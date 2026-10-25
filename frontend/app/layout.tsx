import type { Metadata } from "next";
import "./globals.css";

export const metadata: Metadata = {
  title: "Etyka Hospoda",
};

export default function RootLayout({ children }: LayoutProps<"/">) {
  return (
    <html lang="cs">
      <body>{children}</body>
    </html>
  );
}
