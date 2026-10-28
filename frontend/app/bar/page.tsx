import LogoutButton from "./logout-button";
import styles from "./bar.module.css";

export const instant = false;

export default function BarPage() {
  return (
    <main className={styles.page}>
      <section className={styles.card}>
        <p className={styles.eyebrow}>Hospoda Díra</p>
        <h1>Vítejte v hospodě</h1>
        <p className={styles.intro}>Tady brzy najdete naši nabídku.</p>
        <LogoutButton />
      </section>
    </main>
  );
}
