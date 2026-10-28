"use client";

import type { ButtonHTMLAttributes } from "react";
import styles from "./primary-button.module.css";

interface PrimaryButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
  compact?: boolean;
}

export default function PrimaryButton({
  compact = false,
  className,
  ...props
}: PrimaryButtonProps) {
  const classes = [styles.button, compact ? styles.compact : null, className]
    .filter(Boolean)
    .join(" ");

  return <button className={classes} {...props} />;
}
