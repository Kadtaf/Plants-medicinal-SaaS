import { useEffect, useRef, useState } from "react";
import { Link, NavLink } from "react-router-dom";

const navLinks = [
    { to: "/", label: "Accueil" },
    { to: "/plants", label: "Plantes" },
    { to: "/oils", label: "Huiles essentielles" },
    { to: "/articles", label: "Articles" },
];

const exploreLinks = [
    { label: "Digestion", to: "/plants?category=digestion" },
    { label: "Sommeil", to: "/plants?category=sommeil" },
    { label: "Stress", to: "/oils?category=stress" },
    { label: "Immunité", to: "/plants?category=immunite" },
    { label: "Guides pratiques", to: "/articles?tag=guides" },
    { label: "Sélections affiliées", to: "/articles?tag=recommandations" },
];

export default function Navbar() {
    const [open, setOpen] = useState(false);
    const [exploreOpen, setExploreOpen] = useState(false);
    const [mobileExploreOpen, setMobileExploreOpen] = useState(false);
    const [scrolled, setScrolled] = useState(false);

    const dropdownRef = useRef<HTMLLIElement | null>(null);

    useEffect(() => {
        const onScroll = () => setScrolled(window.scrollY > 8);
        onScroll();
        window.addEventListener("scroll", onScroll);
        return () => window.removeEventListener("scroll", onScroll);
    }, []);

    useEffect(() => {
        const handleClickOutside = (event: MouseEvent) => {
            if (
                dropdownRef.current &&
                !dropdownRef.current.contains(event.target as Node)
            ) {
                setExploreOpen(false);
            }
        };

        document.addEventListener("mousedown", handleClickOutside);
        return () => document.removeEventListener("mousedown", handleClickOutside);
    }, []);

    const toggleTheme = () => {
        const current = document.documentElement.getAttribute("data-theme");
        document.documentElement.setAttribute(
            "data-theme",
            current === "dark" ? "light" : "dark"
        );
    };

    const navShellStyle: React.CSSProperties = {
        background: "var(--color-overlay)",
        border: "1px solid var(--color-border)",
        boxShadow: scrolled ? "var(--shadow-lg)" : "var(--shadow-glass)",
        backdropFilter: "blur(18px)",
        WebkitBackdropFilter: "blur(18px)",
    };

    const ghostButtonStyle: React.CSSProperties = {
        color: "var(--color-text)",
        background: "var(--color-bg-elevated)",
        border: "1px solid var(--color-border)",
    };

    return (
        <header className="sticky top-0 z-50 px-3 pt-3 md:px-5">
            <div className="container-app">
                <div
                    className="rounded-[28px] transition-all duration-300"
                    style={navShellStyle}
                >
                    <nav className="flex items-center gap-4 px-4 py-3 lg:px-6">
                        <Link to="/" className="flex shrink-0 items-center gap-3">
                            <div
                                className="flex h-12 w-12 items-center justify-center rounded-[18px] text-sm font-bold text-white"
                                style={{
                                    background:
                                        "linear-gradient(135deg, var(--color-primary), var(--color-primary-hover))",
                                    boxShadow: "var(--shadow-sm)",
                                }}
                            >
                                PM
                            </div>

                            <div className="hidden min-[430px]:flex flex-col leading-tight">
                <span
                    className="text-sm font-semibold md:text-[15px]"
                    style={{ color: "var(--color-text)" }}
                >
                  Plantes Médicinales
                </span>
                                <span
                                    className="text-xs"
                                    style={{ color: "var(--color-text-muted)" }}
                                >
                  Blog premium & sélections naturelles
                </span>
                            </div>
                        </Link>

                        <div className="hidden min-[1180px]:block flex-1">
                            <div className="mx-auto max-w-xl">
                                <form
                                    className="flex items-center gap-3 rounded-full px-4 py-2.5"
                                    style={{
                                        background: "var(--color-bg-elevated)",
                                        border: "1px solid var(--color-border)",
                                    }}
                                >
                                    <svg
                                        aria-hidden="true"
                                        viewBox="0 0 24 24"
                                        className="h-5 w-5 shrink-0"
                                        fill="none"
                                        stroke="currentColor"
                                        strokeWidth="1.8"
                                        style={{ color: "var(--color-text-muted)" }}
                                    >
                                        <circle cx="11" cy="11" r="7" />
                                        <path d="M20 20L16.65 16.65" />
                                    </svg>

                                    <input
                                        type="text"
                                        placeholder="Rechercher une plante, une huile, un guide..."
                                        className="w-full bg-transparent text-sm outline-none placeholder:text-current"
                                        style={{ color: "var(--color-text)" }}
                                    />

                                    <button
                                        type="submit"
                                        className="btn-primary px-4 py-2 text-xs font-semibold"
                                    >
                                        Rechercher
                                    </button>
                                </form>
                            </div>
                        </div>

                        <div className="hidden lg:flex lg:items-center">
                            <ul className="flex items-center gap-3">
                                {navLinks.map((link) => (
                                    <li key={link.to}>
                                        <NavLink
                                            to={link.to}
                                            className="inline-flex items-center rounded-full px-4 py-2.5 text-sm font-medium transition-all duration-300 hover:-translate-y-[1px]"
                                            style={({ isActive }) => ({
                                                color: isActive
                                                    ? "var(--color-primary)"
                                                    : "var(--color-text-soft)",
                                                background: isActive
                                                    ? "var(--color-primary-soft)"
                                                    : "transparent",
                                                border: isActive
                                                    ? "1px solid var(--color-border)"
                                                    : "1px solid transparent",
                                            })}
                                        >
                                            {link.label}
                                        </NavLink>
                                    </li>
                                ))}

                                <li className="relative" ref={dropdownRef}>
                                    <button
                                        type="button"
                                        onClick={() => setExploreOpen((v) => !v)}
                                        className="inline-flex items-center gap-2 rounded-full px-4 py-2.5 text-sm font-medium transition-all duration-300 hover:-translate-y-[1px]"
                                        style={{ color: "var(--color-text-soft)" }}
                                        aria-expanded={exploreOpen}
                                    >
                                        Explorer
                                        <svg
                                            viewBox="0 0 20 20"
                                            className={`h-4 w-4 transition-transform duration-300 ${exploreOpen ? "rotate-180" : ""}`}
                                            fill="none"
                                            stroke="currentColor"
                                            strokeWidth="1.8"
                                        >
                                            <path d="M5 7.5L10 12.5L15 7.5" />
                                        </svg>
                                    </button>

                                    <div
                                        className={`absolute left-0 top-[calc(100%+12px)] w-[340px] overflow-hidden rounded-[22px] p-2 transition-all duration-300 ${
                                            exploreOpen
                                                ? "visible translate-y-0 opacity-100"
                                                : "invisible -translate-y-2 opacity-0"
                                        }`}
                                        style={{
                                            background: "var(--color-surface)",
                                            border: "1px solid var(--color-border)",
                                            boxShadow: "var(--shadow-lg)",
                                        }}
                                    >
                                        <div className="px-3 pb-2 pt-1">
                                            <p
                                                className="text-[11px] font-semibold uppercase tracking-[0.18em]"
                                                style={{ color: "var(--color-text-muted)" }}
                                            >
                                                Explorer les thématiques
                                            </p>
                                        </div>

                                        <div className="grid grid-cols-2 gap-2">
                                            {exploreLinks.map((item) => (
                                                <Link
                                                    key={item.to}
                                                    to={item.to}
                                                    onClick={() => setExploreOpen(false)}
                                                    className="rounded-[16px] px-3 py-3 text-sm transition-colors duration-200"
                                                    style={{ color: "var(--color-text-soft)" }}
                                                    onMouseEnter={(e) => {
                                                        e.currentTarget.style.background =
                                                            "var(--color-primary-soft)";
                                                        e.currentTarget.style.color = "var(--color-primary)";
                                                    }}
                                                    onMouseLeave={(e) => {
                                                        e.currentTarget.style.background = "transparent";
                                                        e.currentTarget.style.color = "var(--color-text-soft)";
                                                    }}
                                                >
                                                    {item.label}
                                                </Link>
                                            ))}
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>

                        <div className="ml-auto hidden items-center gap-3 lg:flex">
                            <button
                                onClick={toggleTheme}
                                className="inline-flex h-11 items-center justify-center rounded-full px-4 text-sm font-medium transition-all duration-300 hover:-translate-y-[1px]"
                                style={ghostButtonStyle}
                            >
                                Mode
                            </button>

                            <Link
                                to="/login"
                                className="inline-flex h-11 items-center justify-center rounded-full px-4 text-sm font-medium transition-all duration-300 hover:-translate-y-[1px]"
                                style={ghostButtonStyle}
                            >
                                Connexion
                            </Link>

                            <Link
                                to="/register"
                                className="btn-primary text-sm font-semibold"
                            >
                                Inscription
                            </Link>
                        </div>

                        <div className="ml-auto flex items-center gap-2 lg:hidden">
                            <button
                                onClick={toggleTheme}
                                className="inline-flex h-10 w-10 items-center justify-center rounded-full"
                                style={ghostButtonStyle}
                                aria-label="Changer le thème"
                            >
                                ☾
                            </button>

                            <button
                                onClick={() => setOpen((v) => !v)}
                                className="inline-flex h-10 w-10 items-center justify-center rounded-full"
                                style={ghostButtonStyle}
                                aria-label="Ouvrir le menu"
                                aria-expanded={open}
                            >
                                <div className="flex flex-col gap-1.5">
                  <span
                      className={`block h-0.5 w-5 origin-center transition-transform duration-300 ${
                          open ? "translate-y-2 rotate-45" : ""
                      }`}
                      style={{ backgroundColor: "var(--color-text)" }}
                  />
                                    <span
                                        className={`block h-0.5 w-5 transition-opacity duration-300 ${
                                            open ? "opacity-0" : "opacity-100"
                                        }`}
                                        style={{ backgroundColor: "var(--color-text)" }}
                                    />
                                    <span
                                        className={`block h-0.5 w-5 origin-center transition-transform duration-300 ${
                                            open ? "-translate-y-2 -rotate-45" : ""
                                        }`}
                                        style={{ backgroundColor: "var(--color-text)" }}
                                    />
                                </div>
                            </button>
                        </div>
                    </nav>

                    <div className="px-4 pb-4 pt-0 min-[1180px]:hidden">
                        <form
                            className="flex items-center gap-3 rounded-full px-4 py-2.5"
                            style={{
                                background: "var(--color-bg-elevated)",
                                border: "1px solid var(--color-border)",
                            }}
                        >
                            <svg
                                aria-hidden="true"
                                viewBox="0 0 24 24"
                                className="h-5 w-5 shrink-0"
                                fill="none"
                                stroke="currentColor"
                                strokeWidth="1.8"
                                style={{ color: "var(--color-text-muted)" }}
                            >
                                <circle cx="11" cy="11" r="7" />
                                <path d="M20 20L16.65 16.65" />
                            </svg>

                            <input
                                type="text"
                                placeholder="Rechercher..."
                                className="w-full bg-transparent text-sm outline-none placeholder:text-current"
                                style={{ color: "var(--color-text)" }}
                            />
                        </form>
                    </div>

                    <div
                        className={`overflow-hidden border-t transition-all duration-300 lg:hidden ${
                            open ? "max-h-[720px] opacity-100" : "max-h-0 opacity-0"
                        }`}
                        style={{ borderColor: "var(--color-border)" }}
                    >
                        <div className="space-y-3 p-4">
                            {navLinks.map((link) => (
                                <NavLink
                                    key={link.to}
                                    to={link.to}
                                    onClick={() => setOpen(false)}
                                    className="block rounded-[18px] px-4 py-3 text-sm font-medium"
                                    style={({ isActive }) => ({
                                        color: isActive
                                            ? "var(--color-primary)"
                                            : "var(--color-text-soft)",
                                        background: isActive
                                            ? "var(--color-primary-soft)"
                                            : "transparent",
                                    })}
                                >
                                    {link.label}
                                </NavLink>
                            ))}

                            <button
                                type="button"
                                onClick={() => setMobileExploreOpen((v) => !v)}
                                className="flex w-full items-center justify-between rounded-[18px] px-4 py-3 text-sm font-medium"
                                style={{
                                    color: "var(--color-text-soft)",
                                    background: "var(--color-bg-elevated)",
                                    border: "1px solid var(--color-border)",
                                }}
                            >
                                <span>Explorer</span>
                                <svg
                                    viewBox="0 0 20 20"
                                    className={`h-4 w-4 transition-transform duration-300 ${mobileExploreOpen ? "rotate-180" : ""}`}
                                    fill="none"
                                    stroke="currentColor"
                                    strokeWidth="1.8"
                                >
                                    <path d="M5 7.5L10 12.5L15 7.5" />
                                </svg>
                            </button>

                            <div
                                className={`overflow-hidden transition-all duration-300 ${
                                    mobileExploreOpen ? "max-h-96 opacity-100" : "max-h-0 opacity-0"
                                }`}
                            >
                                <div className="grid grid-cols-1 gap-2 pt-2">
                                    {exploreLinks.map((item) => (
                                        <Link
                                            key={item.to}
                                            to={item.to}
                                            onClick={() => {
                                                setOpen(false);
                                                setMobileExploreOpen(false);
                                            }}
                                            className="rounded-[16px] px-4 py-3 text-sm"
                                            style={{
                                                color: "var(--color-text-soft)",
                                                background: "var(--color-surface-2)",
                                            }}
                                        >
                                            {item.label}
                                        </Link>
                                    ))}
                                </div>
                            </div>

                            <Link
                                to="/login"
                                onClick={() => setOpen(false)}
                                className="block rounded-[18px] px-4 py-3 text-sm font-medium"
                                style={{
                                    color: "var(--color-text)",
                                    background: "var(--color-bg-elevated)",
                                    border: "1px solid var(--color-border)",
                                }}
                            >
                                Connexion
                            </Link>

                            <Link
                                to="/register"
                                onClick={() => setOpen(false)}
                                className="btn-primary mt-1 w-full text-sm font-semibold"
                            >
                                Inscription
                            </Link>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    );
}