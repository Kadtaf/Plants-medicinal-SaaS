export default function Footer() {
    return (
        <footer className="border-t border-black/5 bg-[var(--color-surface)] pt-16 pb-10 mt-20">
            <div className="max-w-6xl mx-auto px-4">

                {/* TOP GRID */}
                <div className="grid grid-cols-1 md:grid-cols-4 gap-12">

                    {/* LOGO + DESCRIPTION */}
                    <div className="col-span-1 md:col-span-2">
                        <div className="flex items-center gap-3">
                            <div className="h-10 w-10 rounded-full bg-[var(--color-primary)] flex items-center justify-center text-white font-bold shadow-md">
                                PM
                            </div>
                            <span className="text-lg font-semibold text-[var(--color-text)]">
                Plantes Médicinales
              </span>
                        </div>

                        <p className="mt-4 text-[var(--color-text)]/70 max-w-md leading-relaxed">
                            Votre guide fiable et accessible sur les plantes médicinales, les huiles essentielles
                            et le bien-être naturel. Informations claires, pédagogiques et vérifiées.
                        </p>

                        {/* CTA AFFILIATION */}
                        <a
                            href="/products"
                            className="inline-block mt-6 px-6 py-3 rounded-full bg-[var(--color-primary)] text-white font-semibold shadow-md hover:bg-[var(--color-primary-light)] transition"
                        >
                            Produits recommandés
                        </a>
                    </div>

                    {/* NAVIGATION */}
                    <div>
                        <h3 className="text-sm font-semibold text-[var(--color-text)] mb-4">
                            Navigation
                        </h3>
                        <ul className="space-y-2 text-[var(--color-text)]/70">
                            <li><a href="/medicinal-plants-ui/public" className="hover:text-[var(--color-primary)]">Accueil</a></li>
                            <li><a href="/plants" className="hover:text-[var(--color-primary)]">Plantes</a></li>
                            <li><a href="/oils" className="hover:text-[var(--color-primary)]">Huiles essentielles</a></li>
                            <li><a href="/articles" className="hover:text-[var(--color-primary)]">Articles</a></li>
                            <li><a href="/favorites" className="hover:text-[var(--color-primary)]">Favoris</a></li>
                        </ul>
                    </div>

                    {/* LEGAL */}
                    <div>
                        <h3 className="text-sm font-semibold text-[var(--color-text)] mb-4">
                            Légal
                        </h3>
                        <ul className="space-y-2 text-[var(--color-text)]/70">
                            <li><a href="/legal" className="hover:text-[var(--color-primary)]">Mentions légales</a></li>
                            <li><a href="/privacy" className="hover:text-[var(--color-primary)]">Politique de confidentialité</a></li>
                            <li><a href="/disclaimer" className="hover:text-[var(--color-primary)]">Disclaimer médical</a></li>
                        </ul>
                    </div>
                </div>

                {/* SEPARATOR */}
                <div className="border-t border-black/5 mt-12 pt-6"></div>

                {/* DISCLAIMER */}
                <p className="text-xs text-[var(--color-text)]/60 leading-relaxed max-w-4xl">
                    Les informations présentées sur ce site sont fournies à titre informatif uniquement et ne
                    constituent en aucun cas un avis médical. Consultez toujours un professionnel de santé
                    avant d’utiliser des plantes ou des huiles essentielles, notamment en cas de grossesse,
                    d’allaitement ou de traitement médical.
                </p>

                {/* COPYRIGHT */}
                <div className="mt-6 text-xs text-[var(--color-text)]/50">
                    © {new Date().getFullYear()} Plantes Médicinales — Tous droits réservés.
                </div>
            </div>
        </footer>
    );
}
