
-- ===========================================
-- INSERT UTILISATEURS (seulement si vide)
-- ===========================================

INSERT INTO users (id, email, password, role, created_at) VALUES
                                                              (1, 'user1@example.com', '$2a$10$0G8q0f0x7u0h8u0j8u0jeFJj8u0j8u0j8u0j8u0j8u0j8u0j8u0', 'USER', NOW()),
                                                              (2, 'user2@example.com', '$2a$10$1H9r1g1y8v1i9v1k9v1kfGKk9v1k9v1k9v1k9v1k9v1k9v1k9v1', 'USER', NOW()),
                                                              (3, 'user3@example.com', '$2a$10$2J0s2h2z9w2j0w2l0w2lgHLl0w2l0w2l0w2l0w2l0w2l0w2l0w2', 'USER', NOW())
ON CONFLICT (id) DO NOTHING;


-- ===========================================
-- INSERT PLANTES (seulement si vide)
-- ===========================================

INSERT INTO plants (id, name, description, origin, season_found, image_url, affiliate_link)
VALUES
    (1, 'Lavande', 'Plante médicinale reconnue pour ses propriétés calmantes et antiseptiques.', 'Méditerranée', 'Été', 'https://example.com/lavande.jpg', 'https://amazon.fr/lavande'),
    (2, 'Menthe poivrée', 'Plante aromatique utilisée pour la digestion et la respiration.', 'Europe', 'Été', 'https://example.com/menthe.jpg', 'https://amazon.fr/menthe'),
    (3, 'Camomille', 'Plante douce utilisée pour apaiser le stress et favoriser le sommeil.', 'Europe', 'Printemps', 'https://example.com/camomille.jpg', 'https://amazon.fr/camomille'),
    (4, 'Thym', 'Plante antiseptique puissante utilisée pour les infections respiratoires.', 'Méditerranée', 'Printemps', 'https://example.com/thym.jpg', 'https://amazon.fr/thym'),
    (5, 'Romarin', 'Plante tonique et stimulante, excellente pour la mémoire et la digestion.', 'Méditerranée', 'Printemps', 'https://example.com/romarin.jpg', 'https://amazon.fr/romarin'),
    (6, 'Aloe Vera', 'Plante succulente utilisée pour hydrater et réparer la peau.', 'Afrique', 'Toute l''année', 'https://example.com/aloe.jpg', 'https://amazon.fr/aloe'),
    (7, 'Gingembre', 'Racine stimulante utilisée pour la digestion et l''immunité.', 'Asie', 'Hiver', 'https://example.com/gingembre.jpg', 'https://amazon.fr/gingembre'),
    (8, 'Curcuma', 'Plante anti-inflammatoire puissante grâce à la curcumine.', 'Inde', 'Hiver', 'https://example.com/curcuma.jpg', 'https://amazon.fr/curcuma'),
    (9, 'Eucalyptus', 'Plante respiratoire par excellence, utilisée en inhalation.', 'Australie', 'Été', 'https://example.com/eucalyptus.jpg', 'https://amazon.fr/eucalyptus'),
    (10, 'Ortie', 'Plante reminéralisante riche en fer et en vitamines.', 'Europe', 'Printemps', 'https://example.com/ortie.jpg', 'https://amazon.fr/ortie'),
    (11, 'Sauge', 'Plante purifiante et digestive, utilisée depuis l''Antiquité.', 'Europe', 'Été', 'https://example.com/sauge.jpg', 'https://amazon.fr/sauge'),
    (12, 'Basilic sacré', 'Plante ayurvédique sacrée, adaptogène et anti-stress.', 'Inde', 'Été', 'https://example.com/basilic.jpg', 'https://amazon.fr/basilic'),
    (13, 'Mélisse', 'Plante calmante et digestive, idéale contre l''anxiété.', 'Europe', 'Été', 'https://example.com/melisse.jpg', 'https://amazon.fr/melisse'),
    (14, 'Valériane', 'Plante sédative naturelle utilisée pour le sommeil.', 'Europe', 'Automne', 'https://example.com/valeriane.jpg', 'https://amazon.fr/valeriane'),
    (15, 'Ginseng', 'Racine tonique et énergisante, utilisée en médecine chinoise.', 'Corée', 'Hiver', 'https://example.com/ginseng.jpg', 'https://amazon.fr/ginseng'),
    (16, 'Passiflore', 'Plante calmante utilisée contre le stress et l''insomnie.', 'Amérique du Sud', 'Été', 'https://example.com/passiflore.jpg', 'https://amazon.fr/passiflore'),
    (17, 'Fenouil', 'Plante digestive et antispasmodique.', 'Méditerranée', 'Printemps', 'https://example.com/fenouil.jpg', 'https://amazon.fr/fenouil'),
    (18, 'Calendula', 'Plante cicatrisante et anti-inflammatoire.', 'Europe', 'Été', 'https://example.com/calendula.jpg', 'https://amazon.fr/calendula'),
    (19, 'Arnica', 'Plante anti-hématome et anti-inflammatoire.', 'Europe', 'Été', 'https://example.com/arnica.jpg', 'https://amazon.fr/arnica'),
    (20, 'Réglisse', 'Plante adoucissante et anti-inflammatoire.', 'Asie', 'Printemps', 'https://example.com/reglisse.jpg', 'https://amazon.fr/reglisse')
ON CONFLICT (id) DO NOTHING;
-- ============================
-- TABLE : plant_properties
-- ============================

INSERT INTO plant_properties (plant_id, property)
VALUES
    (1, 'Calmante'), (1, 'Antiseptique'), (1, 'Anti-inflammatoire'),
    (2, 'Digestive'), (2, 'Tonique'), (2, 'Antispasmodique'),
    (3, 'Calmante'), (3, 'Digestive'), (3, 'Anti-inflammatoire'),
    (4, 'Antiseptique'), (4, 'Expectorant'), (4, 'Tonique'),
    (5, 'Stimulant'), (5, 'Digestif'), (5, 'Antioxydant'),
    (6, 'Hydratante'), (6, 'Cicatrisante'), (6, 'Anti-inflammatoire'),
    (7, 'Stimulant'), (7, 'Digestif'), (7, 'Anti-nauséeux'),
    (8, 'Anti-inflammatoire'), (8, 'Antioxydant'), (8, 'Digestif'),
    (9, 'Expectorant'), (9, 'Antiseptique'), (9, 'Décongestionnant'),
    (10, 'Reminéralisante'), (10, 'Anti-inflammatoire'), (10, 'Dépurative');
-- ============================
-- TABLE : plant_uses
-- ============================

INSERT INTO plant_uses (plant_id, use)
VALUES
    (1, 'Infusion relaxante'), (1, 'Huile essentielle'), (1, 'Soin de la peau'),
    (2, 'Infusion digestive'), (2, 'Huile essentielle'), (2, 'Inhalation'),
    (3, 'Infusion'), (3, 'Compresses'), (3, 'Cosmétiques'),
    (4, 'Infusion'), (4, 'Inhalation'), (4, 'Cuisine'),
    (5, 'Infusion'), (5, 'Huile essentielle'), (5, 'Cuisine'),
    (6, 'Gel cutané'), (6, 'Cosmétiques'), (6, 'Soin des brûlures'),
    (7, 'Infusion'), (7, 'Cuisine'), (7, 'Compléments'),
    (8, 'Cuisine'), (8, 'Compléments'), (8, 'Infusion'),
    (9, 'Inhalation'), (9, 'Huile essentielle'), (9, 'Infusion'),
    (10, 'Infusion'), (10, 'Soupe'), (10, 'Compléments');

-- ============================
-- TABLE : oils
-- ============================

INSERT INTO oils (id, name, description, image_url, affiliate_link, plant_id)
VALUES
    (1, 'Huile essentielle de Lavande', 'Huile calmante et apaisante, idéale pour le sommeil et la peau.', 'https://example.com/huile-lavande.jpg', 'https://amazon.fr/huile-lavande', 1),
    (2, 'Huile essentielle de Menthe poivrée', 'Huile rafraîchissante et digestive, utilisée contre les maux de tête.', 'https://example.com/huile-menthe.jpg', 'https://amazon.fr/huile-menthe', 2),
    (3, 'Huile essentielle de Camomille', 'Huile douce et calmante, parfaite pour les irritations et le stress.', 'https://example.com/huile-camomille.jpg', 'https://amazon.fr/huile-camomille', 3),
    (4, 'Huile essentielle de Thym', 'Huile antiseptique puissante, idéale pour les infections respiratoires.', 'https://example.com/huile-thym.jpg', 'https://amazon.fr/huile-thym', 4),
    (5, 'Huile essentielle de Romarin', 'Huile tonique et stimulante, excellente pour la concentration.', 'https://example.com/huile-romarin.jpg', 'https://amazon.fr/huile-romarin', 5),
    (6, 'Huile d’Aloe Vera', 'Huile hydratante et réparatrice pour la peau.', 'https://example.com/huile-aloe.jpg', 'https://amazon.fr/huile-aloe', 6),
    (7, 'Huile essentielle de Gingembre', 'Huile chauffante et stimulante, idéale pour la digestion.', 'https://example.com/huile-gingembre.jpg', 'https://amazon.fr/huile-gingembre', 7),
    (8, 'Huile essentielle de Curcuma', 'Huile anti-inflammatoire puissante, riche en curcumine.', 'https://example.com/huile-curcuma.jpg', 'https://amazon.fr/huile-curcuma', 8),
    (9, 'Huile essentielle d’Eucalyptus', 'Huile respiratoire, décongestionnante et antiseptique.', 'https://example.com/huile-eucalyptus.jpg', 'https://amazon.fr/huile-eucalyptus', 9),
    (10, 'Huile d’Ortie', 'Huile fortifiante pour les cheveux et la peau.', 'https://example.com/huile-ortie.jpg', 'https://amazon.fr/huile-ortie', 10),
    (11, 'Huile essentielle de Sauge', 'Huile purifiante et équilibrante.', 'https://example.com/huile-sauge.jpg', 'https://amazon.fr/huile-sauge', 11),
    (12, 'Huile de Basilic sacré', 'Huile adaptogène, anti-stress et revitalisante.', 'https://example.com/huile-basilic.jpg', 'https://amazon.fr/huile-basilic', 12),
    (13, 'Huile essentielle de Mélisse', 'Huile calmante, idéale contre l’anxiété.', 'https://example.com/huile-melisse.jpg', 'https://amazon.fr/huile-melisse', 13),
    (14, 'Huile essentielle de Valériane', 'Huile sédative naturelle pour le sommeil.', 'https://example.com/huile-valeriane.jpg', 'https://amazon.fr/huile-valeriane', 14),
    (15, 'Huile de Ginseng', 'Huile tonique et énergisante.', 'https://example.com/huile-ginseng.jpg', 'https://amazon.fr/huile-ginseng', 15),
    (16, 'Huile de Passiflore', 'Huile calmante et apaisante.', 'https://example.com/huile-passiflore.jpg', 'https://amazon.fr/huile-passiflore', 16),
    (17, 'Huile essentielle de Fenouil', 'Huile digestive et antispasmodique.', 'https://example.com/huile-fenouil.jpg', 'https://amazon.fr/huile-fenouil', 17),
    (18, 'Huile de Calendula', 'Huile cicatrisante et anti-inflammatoire.', 'https://example.com/huile-calendula.jpg', 'https://amazon.fr/huile-calendula', 18),
    (19, 'Huile d’Arnica', 'Huile anti-hématome et apaisante.', 'https://example.com/huile-arnica.jpg', 'https://amazon.fr/huile-arnica', 19),
    (20, 'Huile de Réglisse', 'Huile adoucissante et anti-inflammatoire.', 'https://example.com/huile-reglisse.jpg', 'https://amazon.fr/huile-reglisse', 20)
ON CONFLICT (id) DO NOTHING;

-- ============================
-- TABLE : oil_benefits
-- ============================

INSERT INTO oil_benefits (oil_id, benefit)
VALUES
    (1, 'Calmante'), (1, 'Relaxante'), (1, 'Apaisante'),
    (2, 'Digestive'), (2, 'Tonique'), (2, 'Antimigraineuse'),
    (3, 'Calmante'), (3, 'Anti-irritation'), (3, 'Relaxante'),
    (4, 'Antiseptique'), (4, 'Expectorante'), (4, 'Purifiante'),
    (5, 'Stimulante'), (5, 'Tonique'), (5, 'Concentration'),
    (6, 'Hydratante'), (6, 'Cicatrisante'), (6, 'Apaisante'),
    (7, 'Stimulante'), (7, 'Digestive'), (7, 'Réchauffante'),
    (8, 'Anti-inflammatoire'), (8, 'Antioxydante'), (8, 'Détoxifiante'),
    (9, 'Respiratoire'), (9, 'Décongestionnante'), (9, 'Antiseptique'),
    (10, 'Fortifiante'), (10, 'Revitalisante'), (10, 'Tonique'),
    (11, 'Purifiante'), (11, 'Équilibrante'), (11, 'Tonique'),
    (12, 'Anti-stress'), (12, 'Adaptogène'), (12, 'Revitalisante'),
    (13, 'Calmante'), (13, 'Anti-anxiété'), (13, 'Relaxante'),
    (14, 'Sédative'), (14, 'Relaxante'), (14, 'Sommeil'),
    (15, 'Énergisante'), (15, 'Tonique'), (15, 'Stimulante'),
    (16, 'Calmante'), (16, 'Apaisante'), (16, 'Anti-stress'),
    (17, 'Digestive'), (17, 'Antispasmodique'), (17, 'Détoxifiante'),
    (18, 'Cicatrisante'), (18, 'Anti-inflammatoire'), (18, 'Apaisante'),
    (19, 'Anti-hématome'), (19, 'Anti-inflammatoire'), (19, 'Apaisante'),
    (20, 'Adoucissante'), (20, 'Anti-inflammatoire'), (20, 'Protectrice');

-- ============================
-- TABLE : oil_precautions
-- ============================

INSERT INTO oil_precautions (oil_id, precaution)
VALUES
    (1, 'Éviter le contact avec les yeux'),
    (1, 'Déconseillée aux femmes enceintes'),
    (2, 'Ne pas utiliser avant le coucher'),
    (2, 'Diluer avant application'),
    (3, 'Test cutané recommandé'),
    (4, 'Ne pas utiliser chez les enfants'),
    (5, 'Éviter en cas d’hypertension'),
    (6, 'Usage externe uniquement'),
    (7, 'Peut irriter la peau'),
    (8, 'Déconseillée en cas de grossesse'),
    (9, 'Ne pas utiliser pure'),
    (10, 'Éviter le contour des yeux'),
    (11, 'Déconseillée en cas d’épilepsie'),
    (12, 'Ne pas ingérer'),
    (13, 'Usage modéré recommandé'),
    (14, 'Déconseillée avec somnifères'),
    (15, 'Éviter le soir'),
    (16, 'Test cutané obligatoire'),
    (17, 'Déconseillée aux enfants'),
    (18, 'Usage externe uniquement'),
    (19, 'Ne pas appliquer sur plaie ouverte'),
    (20, 'Déconseillée en cas d’hypertension');


-- ============================
-- TABLE : articles
-- ============================

INSERT INTO articles (id, title, slug, content, category, read_time, image_url, created_at)
VALUES
    (1, 'Les bienfaits de la lavande pour le sommeil', 'bienfaits-lavande-sommeil',
     'La lavande est l’une des plantes les plus utilisées pour favoriser la détente et améliorer la qualité du sommeil. Ses propriétés calmantes...',
     'Plantes médicinales', 4, 'https://example.com/article-lavande.jpg', NOW()),

    (2, 'Comment utiliser la menthe poivrée contre les maux de tête', 'menthe-poivree-maux-de-tete',
     'L’huile essentielle de menthe poivrée est un remède naturel puissant contre les migraines grâce à son effet rafraîchissant...',
     'Huiles essentielles', 3, 'https://example.com/article-menthe.jpg', NOW()),

    (3, 'Camomille : une alliée contre le stress', 'camomille-anti-stress',
     'La camomille est reconnue pour ses propriétés calmantes. En infusion, elle aide à réduire l’anxiété et à apaiser le système nerveux...',
     'Bien-être', 5, 'https://example.com/article-camomille.jpg', NOW()),

    (4, 'Le thym : un antiseptique naturel puissant', 'thym-antiseptique-naturel',
     'Le thym est utilisé depuis l’Antiquité pour ses propriétés antiseptiques. En inhalation, il aide à dégager les voies respiratoires...',
     'Plantes médicinales', 4, 'https://example.com/article-thym.jpg', NOW()),

    (5, 'Pourquoi le romarin améliore la concentration', 'romarin-concentration',
     'Le romarin stimule la circulation sanguine et améliore la mémoire. Son huile essentielle est idéale pour booster la concentration...',
     'Huiles essentielles', 3, 'https://example.com/article-romarin.jpg', NOW()),

    (6, 'Aloe Vera : la plante miracle pour la peau', 'aloe-vera-peau',
     'L’Aloe Vera hydrate, apaise et répare la peau. Son gel est utilisé dans de nombreux soins naturels...',
     'Beauté naturelle', 4, 'https://example.com/article-aloe.jpg', NOW()),

    (7, 'Gingembre : un stimulant naturel pour l’immunité', 'gingembre-immunite',
     'Le gingembre est un puissant stimulant naturel. Il aide à renforcer le système immunitaire et à améliorer la digestion...',
     'Nutrition', 4, 'https://example.com/article-gingembre.jpg', NOW()),

    (8, 'Curcuma : l’anti-inflammatoire naturel', 'curcuma-anti-inflammatoire',
     'Le curcuma est riche en curcumine, un composé aux propriétés anti-inflammatoires exceptionnelles...',
     'Plantes médicinales', 5, 'https://example.com/article-curcuma.jpg', NOW()),

    (9, 'Eucalyptus : comment dégager les voies respiratoires', 'eucalyptus-respiration',
     'L’eucalyptus est utilisé en inhalation pour dégager les voies respiratoires et lutter contre les infections hivernales...',
     'Bien-être', 3, 'https://example.com/article-eucalyptus.jpg', NOW()),

    (10, 'Ortie : une plante riche en minéraux', 'ortie-mineraux',
     'L’ortie est une plante reminéralisante riche en fer, calcium et vitamines. Elle est idéale en infusion ou en complément...',
     'Nutrition', 4, 'https://example.com/article-ortie.jpg', NOW())
ON CONFLICT (id) DO NOTHING;

-- ============================
-- TABLE : affiliate_products
-- ============================

INSERT INTO affiliate_products (id, name, url, vendor, image_url, price, category, associated_plant_id)
VALUES
    (1, 'Huile essentielle de Lavande Bio 10ml', 'https://amazon.fr/huile-lavande', 'Amazon', 'https://example.com/prod-lavande.jpg', 8.99, 'Huiles essentielles', 1),
    (2, 'Menthe poivrée Bio – Flacon 10ml', 'https://amazon.fr/huile-menthe', 'Amazon', 'https://example.com/prod-menthe.jpg', 7.49, 'Huiles essentielles', 2),
    (3, 'Camomille Matricaire – Infusion Bio', 'https://amazon.fr/camomille-bio', 'Amazon', 'https://example.com/prod-camomille.jpg', 5.99, 'Infusions', 3),
    (4, 'Thym Bio – Sachets infusion', 'https://amazon.fr/thym-bio', 'Amazon', 'https://example.com/prod-thym.jpg', 4.99, 'Infusions', 4),
    (5, 'Romarin Bio – Huile essentielle 10ml', 'https://amazon.fr/huile-romarin', 'Amazon', 'https://example.com/prod-romarin.jpg', 9.49, 'Huiles essentielles', 5),
    (6, 'Gel Aloe Vera Pur 99%', 'https://amazon.fr/aloe-vera-gel', 'Amazon', 'https://example.com/prod-aloe.jpg', 12.99, 'Soins naturels', 6),
    (7, 'Gingembre en poudre Bio 200g', 'https://amazon.fr/gingembre-poudre', 'Amazon', 'https://example.com/prod-gingembre.jpg', 6.49, 'Épices', 7),
    (8, 'Curcuma Bio 200g', 'https://amazon.fr/curcuma-bio', 'Amazon', 'https://example.com/prod-curcuma.jpg', 6.99, 'Épices', 8),
    (9, 'Eucalyptus – Huile essentielle 10ml', 'https://amazon.fr/huile-eucalyptus', 'Amazon', 'https://example.com/prod-eucalyptus.jpg', 7.99, 'Huiles essentielles', 9),
    (10, 'Ortie Bio – Complément alimentaire', 'https://amazon.fr/ortie-bio', 'Amazon', 'https://example.com/prod-ortie.jpg', 14.99, 'Compléments', 10)
ON CONFLICT (id) DO NOTHING;

-- ============================
-- TABLE : favorites
-- ============================

INSERT INTO favorites (id, user_id, target_id, type, pinned, created_at)
VALUES
-- Utilisateur 1
(1, 1, 1, 'PLANT', false, NOW()),
(2, 1, 2, 'PLANT', false, NOW()),
(3, 1, 3, 'PLANT', false, NOW()),
(4, 1, 1, 'OIL', false, NOW()),
(5, 1, 5, 'OIL', false, NOW()),
(6, 1, 7, 'PLANT', false, NOW()),
(7, 1, 9, 'OIL', false, NOW()),
(8, 1, 10, 'PLANT', false, NOW()),
(9, 1, 4, 'OIL', false, NOW()),
(10, 1, 8, 'PLANT', false, NOW()),

-- Utilisateur 2
(11, 2, 6, 'PLANT', false, NOW()),
(12, 2, 7, 'PLANT', false, NOW()),
(13, 2, 8, 'PLANT', false, NOW()),
(14, 2, 6, 'OIL', false, NOW()),
(15, 2, 7, 'OIL', false, NOW()),
(16, 2, 8, 'OIL', false, NOW()),
(17, 2, 9, 'PLANT', false, NOW()),
(18, 2, 10, 'OIL', false, NOW()),
(19, 2, 12, 'PLANT', false, NOW()),
(20, 2, 14, 'PLANT', false, NOW()),

-- Utilisateur 3
(21, 3, 11, 'PLANT', false, NOW()),
(22, 3, 12, 'PLANT', false, NOW()),
(23, 3, 13, 'PLANT', false, NOW()),
(24, 3, 11, 'OIL', false, NOW()),
(25, 3, 12, 'OIL', false, NOW()),
(26, 3, 13, 'OIL', false, NOW()),
(27, 3, 15, 'PLANT', false, NOW()),
(28, 3, 16, 'PLANT', false, NOW()),
(29, 3, 17, 'PLANT', false, NOW()),
(30, 3, 18, 'OIL', false, NOW())
ON CONFLICT (user_id, target_id, type) DO NOTHING;

-- ============================
-- TABLE : comments
-- ============================

INSERT INTO comments (id, user_id, target_id, type, content, visible, created_at)
VALUES
-- PLANTES
(1, 1, 1, 'PLANT', 'La lavande m’aide vraiment à mieux dormir.', false, NOW()),
(2, 2, 1, 'PLANT', 'Super plante pour la relaxation.', false, NOW()),
(3, 3, 2, 'PLANT', 'La menthe poivrée est top pour la digestion.', false, NOW()),
(4, 1, 3, 'PLANT', 'La camomille m’a beaucoup aidé contre le stress.', false, NOW()),
(5, 2, 4, 'PLANT', 'Le thym est excellent en inhalation.', false, NOW()),
(6, 3, 5, 'PLANT', 'Le romarin améliore vraiment ma concentration.', false, NOW()),
(7, 1, 6, 'PLANT', 'Aloe vera indispensable pour les brûlures.', false, NOW()),
(8, 2, 7, 'PLANT', 'Le gingembre booste mon énergie.', false, NOW()),
(9, 3, 8, 'PLANT', 'Le curcuma est un anti-inflammatoire naturel.', false, NOW()),
(10, 1, 9, 'PLANT', 'Eucalyptus parfait pour les rhumes.', false, NOW()),

-- HUILES
(11, 1, 1, 'OIL', 'Huile de lavande très efficace pour dormir.', false, NOW()),
(12, 2, 2, 'OIL', 'Menthe poivrée géniale contre les migraines.', false, NOW()),
(13, 3, 3, 'OIL', 'Huile de camomille très douce.', false, NOW()),
(14, 1, 4, 'OIL', 'Huile de thym très puissante.', false, NOW()),
(15, 2, 5, 'OIL', 'Huile de romarin parfaite pour se concentrer.', false, NOW()),
(16, 3, 6, 'OIL', 'Aloe vera très hydratante.', false, NOW()),
(17, 1, 7, 'OIL', 'Huile de gingembre chauffante.', false, NOW()),
(18, 2, 8, 'OIL', 'Huile de curcuma anti-inflammatoire.', false, NOW()),
(19, 3, 9, 'OIL', 'Eucalyptus très efficace en inhalation.', false, NOW()),
(20, 1, 10, 'OIL', 'Huile d’ortie fortifiante.', false, NOW()),

-- ARTICLES
(21, 1, 1, 'ARTICLE', 'Article très complet sur la lavande.', false, NOW()),
(22, 2, 2, 'ARTICLE', 'Très utile pour les migraines.', false, NOW()),
(23, 3, 3, 'ARTICLE', 'J’adore la camomille, super article.', false, NOW()),
(24, 1, 4, 'ARTICLE', 'Le thym est vraiment puissant.', false, NOW()),
(25, 2, 5, 'ARTICLE', 'Le romarin m’aide à me concentrer.', false, NOW()),
(26, 3, 6, 'ARTICLE', 'Aloe vera indispensable.', false, NOW()),
(27, 1, 7, 'ARTICLE', 'Le gingembre est un super stimulant.', false, NOW()),
(28, 2, 8, 'ARTICLE', 'Le curcuma est incroyable.', false, NOW()),
(29, 3, 9, 'ARTICLE', 'Eucalyptus parfait pour l’hiver.', false, NOW()),
(30, 1, 10, 'ARTICLE', 'L’ortie est sous-estimée.', false, NOW()),

-- Commentaires supplémentaires pour atteindre 50
(31, 1, 11, 'PLANT', 'La sauge est très purifiante.', false, NOW()),
(32, 2, 12, 'PLANT', 'Le basilic sacré m’aide à gérer le stress.', false, NOW()),
(33, 3, 13, 'PLANT', 'La mélisse est super pour l’anxiété.', false, NOW()),
(34, 1, 14, 'PLANT', 'La valériane m’aide à dormir.', false, NOW()),
(35, 2, 15, 'PLANT', 'Le ginseng me donne de l’énergie.', false, NOW()),
(36, 3, 16, 'PLANT', 'La passiflore est très apaisante.', false, NOW()),
(37, 1, 17, 'PLANT', 'Le fenouil aide vraiment la digestion.', false, NOW()),
(38, 2, 18, 'PLANT', 'Le calendula est top pour la peau.', false, NOW()),
(39, 3, 19, 'PLANT', 'Arnica indispensable après le sport.', false, NOW()),
(40, 1, 20, 'PLANT', 'La réglisse apaise la gorge.', false, NOW()),

(41, 2, 11, 'OIL', 'Huile de sauge très efficace.', false, NOW()),
(42, 3, 12, 'OIL', 'Huile de basilic sacrée anti-stress.', false, NOW()),
(43, 1, 13, 'OIL', 'Huile de mélisse très douce.', false, NOW()),
(44, 2, 14, 'OIL', 'Huile de valériane pour dormir.', false, NOW()),
(45, 3, 15, 'OIL', 'Huile de ginseng énergisante.', false, NOW()),
(46, 1, 16, 'OIL', 'Huile de passiflore apaisante.', false, NOW()),
(47, 2, 17, 'OIL', 'Huile de fenouil digestive.', false, NOW()),
(48, 3, 18, 'OIL', 'Huile de calendula cicatrisante.', false, NOW()),
(49, 1, 19, 'OIL', 'Huile d’arnica anti-hématome.', false, NOW()),
(50, 2, 20, 'OIL', 'Huile de réglisse adoucissante.', false, NOW())
ON CONFLICT (id) DO NOTHING;