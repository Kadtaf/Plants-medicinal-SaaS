import { Outlet } from "react-router-dom";
import Navbar from "@/widgets/navbar/Navbar";

export default function MainLayout() {
    return (
        <div className="min-h-screen bg-[var(--color-bg)] text-[var(--color-text)]">
            <Navbar />
            <main className="mx-auto max-w-6xl px-4 py-6 md:py-8">
                <Outlet />
            </main>
            <footer className="border-t border-black/5 bg-[var(--color-surface)]">
                <div className="mx-auto max-w-6xl px-4 py-4 text-xs text-[var(--color-text)]/60">
                    © {new Date().getFullYear()} Plantes Médicinales – Informations à titre indicatif, pas de conseil médical.
                </div>
            </footer>
        </div>
    );
}