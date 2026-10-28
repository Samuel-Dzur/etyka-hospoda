"use client";

import { useState } from "react";
import { useAuth } from "@/app/auth-provider";
import PrimaryButton from "@/app/components/primary-button";
import feedbackStyles from "@/app/components/feedback.module.css";

export default function LogoutButton() {
  const { logout } = useAuth();
  const [logoutError, setLogoutError] = useState<string | null>(null);

  async function handleLogout() {
    setLogoutError(null);
    try {
      await logout();
    } catch (error) {
      setLogoutError(
        error instanceof Error
          ? error.message
          : "Odhlášení se nepodařilo. Zkuste to prosím znovu.",
      );
    }
  }

  return (
    <>
      {logoutError && (
        <p className={feedbackStyles.error} role="alert">
          {logoutError}
        </p>
      )}
      <PrimaryButton compact type="button" onClick={handleLogout}>
        Odhlásit se
      </PrimaryButton>
    </>
  );
}
