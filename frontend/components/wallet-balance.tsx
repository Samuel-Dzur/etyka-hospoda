"use client";

import { Wallet } from "lucide-react";
import styles from "./account-info.module.css";

export default function WalletBalance() {
  return (
    <p className={styles.accountItem}>
      <Wallet size={20} aria-hidden="true" />
      <span>800 Kč</span>
    </p>
  );
}
