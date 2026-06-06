export default function HomePage() {
    return (
        <div className="space-y-24">
            {/* HERO */}
            <section className="pt-20 pb-24 text-center">
                <h1 className="text-4xl md:text-6xl font-extrabold text-[var(--color-text)] leading-tight">
                    Découvrez les <span className="text-[var(--color-primary)]">plantes médicinales</span>
                    <br />
                    et leurs bienfaits naturels
                </h1>

                <p className="mt-6 text-lg md:text-xl text-[var(--color-text)]/70 max-w-2xl mx-auto">
                    Une base de connaissances fiable, claire et accessible pour mieux comprendre les plantes,
                    les huiles essentielles et leurs usages au quotidien.
                </p>

                <div className="mt-10 flex justify-center gap-4">
                    <a
                        href="/plants"
                        className="px-6 py-3 rounded-full bg-[var(--color-primary)] text-white font-semibold shadow-md hover:bg-[var(--color-primary-light)] transition"
                    >
                        Explorer les plantes
                    </a>

                    <a
                        href="/products"
                        className="px-6 py-3 rounded-full border border-[var(--color-primary)] text-[var(--color-primary)] font-semibold hover:bg-[var(--color-primary)] hover:text-white transition"
                    >
                        Produits recommandés
                    </a>
                </div>
            </section>

            {/* SECTION 1 — PLANTES */}
            <section className="max-w-6xl mx-auto px-4">
                <h2 className="text-3xl font-bold text-[var(--color-text)] mb-6">
                    🌿 Plantes médicinales
                </h2>

                <p className="text-[var(--color-text)]/70 max-w-2xl">
                    Découvrez les plantes les plus utilisées en phytothérapie : leurs propriétés, leurs
                    bienfaits, leurs modes d’utilisation et leurs précautions.
                </p>

                <div className="mt-8">
                    <a
                        href="/plants"
                        className="inline-block px-5 py-2 rounded-full bg-[var(--color-primary)] text-white font-semibold shadow hover:bg-[var(--color-primary-light)] transition"
                    >
                        Voir toutes les plantes
                    </a>
                </div>
            </section>

            {/* SECTION 2 — HUILES ESSENTIELLES */}
            <section className="max-w-6xl mx-auto px-4">
                <h2 className="text-3xl font-bold text-[var(--color-text)] mb-6">
                    🍃 Huiles essentielles
                </h2>

                <p className="text-[var(--color-text)]/70 max-w-2xl">
                    Une sélection d’huiles essentielles incontournables, avec leurs usages, leurs propriétés
                    thérapeutiques et les précautions indispensables.
                </p>

                <div className="mt-8">
                    <a
                        href="/oils"
                        className="inline-block px-5 py-2 rounded-full bg-[var(--color-primary)] text-white font-semibold shadow hover:bg-[var(--color-primary-light)] transition"
                    >
                        Voir les huiles essentielles
                    </a>
                </div>
            </section>

            {/* SECTION 3 — ARTICLES */}
            <section className="max-w-6xl mx-auto px-4">
                <h2 className="text-3xl font-bold text-[var(--color-text)] mb-6">
                    📚 Articles & guides
                </h2>

                <p className="text-[var(--color-text)]/70 max-w-2xl">
                    Des articles clairs et pédagogiques pour comprendre comment utiliser les plantes et les
                    huiles essentielles dans votre quotidien.
                </p>

                <div className="mt-8">
                    <a
                        href="/articles"
                        className="inline-block px-5 py-2 rounded-full bg-[var(--color-primary)] text-white font-semibold shadow hover:bg-[var(--color-primary-light)] transition"
                    >
                        Lire les articles
                    </a>
                </div>
            </section>

            {/* SECTION 4 — CTA AFFILIATION */}
            <section className="py-20 bg-[var(--color-surface)] border-t border-black/5">
                <div className="max-w-4xl mx-auto text-center px-4">
                    <h2 className="text-3xl md:text-4xl font-bold text-[var(--color-text)]">
                        🌟 Produits recommandés pour votre bien-être
                    </h2>

                    <p className="mt-4 text-[var(--color-text)]/70 text-lg">
                        Une sélection de produits de qualité, testés et approuvés, pour vous accompagner dans
                        votre pratique du bien-être naturel.
                    </p>

                    <a
                        href="/products"
                        className="mt-8 inline-block px-8 py-3 rounded-full bg-[var(--color-primary)] text-white font-semibold shadow-lg hover:bg-[var(--color-primary-light)] transition"
                    >
                        Voir les produits recommandés
                    </a>
                </div>
            </section>
        </div>
    );
}
