export default function Dashboard() {
    return (
        <section className="space-y-6">
            <header className="space-y-2">
                <p className="text-sm font-medium uppercase tracking-[0.16em] text-[var(--color-primary)]">
                    Administration
                </p>
                <h1 className="text-3xl font-semibold text-[var(--color-text)]">
                    Dashboard
                </h1>
                <p className="max-w-2xl text-sm text-[var(--color-text-soft)] md:text-base">
                    Gérez les plantes, les huiles, les contenus et les paramètres de la
                    plateforme depuis une interface centralisée.
                </p>
            </header>

            <div className="grid gap-4 md:grid-cols-2 xl:grid-cols-4">
                <article className="rounded-[var(--radius-lg)] border border-[var(--color-border)] bg-[var(--color-surface)] p-5 shadow-[var(--shadow-sm)]">
                    <p className="text-sm text-[var(--color-text-soft)]">Plantes</p>
                    <p className="mt-2 text-2xl font-semibold text-[var(--color-text)]">--</p>
                </article>

                <article className="rounded-[var(--radius-lg)] border border-[var(--color-border)] bg-[var(--color-surface)] p-5 shadow-[var(--shadow-sm)]">
                    <p className="text-sm text-[var(--color-text-soft)]">Huiles</p>
                    <p className="mt-2 text-2xl font-semibold text-[var(--color-text)]">--</p>
                </article>

                <article className="rounded-[var(--radius-lg)] border border-[var(--color-border)] bg-[var(--color-surface)] p-5 shadow-[var(--shadow-sm)]">
                    <p className="text-sm text-[var(--color-text-soft)]">Utilisateurs</p>
                    <p className="mt-2 text-2xl font-semibold text-[var(--color-text)]">--</p>
                </article>

                <article className="rounded-[var(--radius-lg)] border border-[var(--color-border)] bg-[var(--color-surface)] p-5 shadow-[var(--shadow-sm)]">
                    <p className="text-sm text-[var(--color-text-soft)]">Contenus publiés</p>
                    <p className="mt-2 text-2xl font-semibold text-[var(--color-text)]">--</p>
                </article>
            </div>

            <article className="rounded-[var(--radius-lg)] border border-[var(--color-border)] bg-[var(--color-surface)] p-6 shadow-[var(--shadow-sm)]">
                <h2 className="text-lg font-semibold text-[var(--color-text)]">
                    Espace d’administration
                </h2>
                <p className="mt-2 max-w-3xl text-sm text-[var(--color-text-soft)] md:text-base">
                    Cette zone servira à piloter les catalogues, les contenus éditoriaux,
                    les validations, et plus tard les métriques métier du SaaS.
                </p>
            </article>
        </section>
    );
}