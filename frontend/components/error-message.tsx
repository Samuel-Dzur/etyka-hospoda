import type { ReactNode } from "react";
import styles from "./error-message.module.css";

export default function ErrorMessage({ children }: { children: ReactNode }) {
  return (
    <p className={styles.error} role="alert">
      {children}
    </p>
  );
}
