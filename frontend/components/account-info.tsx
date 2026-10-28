import Profile from "./profile";
import WalletBalance from "./wallet-balance";
import styles from "./account-info.module.css";

export default function AccountInfo() {
  return (
    <div className={styles.accountInfo}>
      <WalletBalance />
      <Profile />
    </div>
  );
}
