"use client";

import { usePathname, useRouter } from "next/navigation";
import {
  createContext,
  useCallback,
  useContext,
  useEffect,
  useMemo,
  useState,
  type ReactNode,
} from "react";
import {
  initializeKeycloak,
  loginWithKeycloak,
  logoutFromKeycloak,
  registerWithKeycloak,
} from "@/lib/keycloak";
import styles from "./auth-provider.module.css";

type AuthStatus = "loading" | "authenticated" | "unauthenticated" | "error";

interface AuthContextValue {
  error: string | null;
  login: (returnTo: string | null) => Promise<void>;
  logout: () => Promise<void>;
  register: (returnTo: string | null) => Promise<void>;
  status: AuthStatus;
}

const loginPath = "/login";
const AuthContext = createContext<AuthContextValue | null>(null);

function safeReturnPath(returnTo: string | null): string {
  if (
    !returnTo ||
    !returnTo.startsWith("/") ||
    returnTo.startsWith("//") ||
    returnTo.startsWith(loginPath) ||
    returnTo.includes("\\")
  ) {
    return "/bar";
  }

  return returnTo;
}

function getErrorMessage(error: unknown): string {
  return error instanceof Error
    ? error.message
    : "Při připojování k přihlašovací službě došlo k neznámé chybě.";
}

export function useAuth(): AuthContextValue {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth must be used inside AuthProvider.");
  }
  return context;
}

export function AuthProvider({ children }: { children: ReactNode }) {
  const pathname = usePathname();
  const router = useRouter();
  const [status, setStatus] = useState<AuthStatus>("loading");
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    let mounted = true;

    initializeKeycloak()
      .then((authenticated) => {
        if (mounted) {
          setStatus(authenticated ? "authenticated" : "unauthenticated");
        }
      })
      .catch((initializationError: unknown) => {
        if (mounted) {
          setError(getErrorMessage(initializationError));
          setStatus("error");
        }
      });

    return () => {
      mounted = false;
    };
  }, []);

  useEffect(() => {
    if (status === "unauthenticated" && pathname !== loginPath) {
      const currentPath = `${window.location.pathname}${window.location.search}`;
      router.replace(`${loginPath}?returnTo=${encodeURIComponent(currentPath)}`);
      return;
    }

    if (status === "authenticated" && pathname === loginPath) {
      const returnTo = new URLSearchParams(window.location.search).get("returnTo");
      router.replace(safeReturnPath(returnTo));
    }
  }, [pathname, router, status]);

  const login = useCallback(async (returnTo: string | null) => {
    const redirectPath = safeReturnPath(returnTo);
    await loginWithKeycloak(`${window.location.origin}${redirectPath}`);
  }, []);

  const register = useCallback(async (returnTo: string | null) => {
    const redirectPath = safeReturnPath(returnTo);
    await registerWithKeycloak(`${window.location.origin}${redirectPath}`);
  }, []);

  const logout = useCallback(async () => {
    await logoutFromKeycloak(`${window.location.origin}${loginPath}`);
  }, []);

  const value = useMemo(
    () => ({ error, login, logout, register, status }),
    [error, login, logout, register, status],
  );

  if (pathname !== loginPath && status !== "authenticated") {
    return (
      <main className={styles.status} role={status === "error" ? "alert" : "status"}>
        <div className={styles.statusCard}>
          {status === "error" ? (
            <>
              <strong>Nepodařilo se ověřit přihlášení</strong>
              {error}
            </>
          ) : (
            <>
              <strong>Ověřujeme přihlášení</strong>
              Počkejte prosím, připojujeme se k přihlašovací službě.
            </>
          )}
        </div>
      </main>
    );
  }

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}
