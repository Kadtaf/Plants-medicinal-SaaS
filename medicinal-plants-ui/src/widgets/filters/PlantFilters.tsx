import { useState } from 'react';

interface PlantFiltersProps {
  onFilterChange?: (filters: FilterState) => void;
}

interface FilterState {
  category: string;
  usage: string;
  sortBy: string;
}

const CATEGORIES = [
  { value: '', label: 'Toutes catégories' },
  { value: 'digestive', label: 'Digestive' },
  { value: 'relaxante', label: 'Relaxante' },
  { value: 'immunitaire', label: 'Immunitaire' },
  { value: 'tonique', label: 'Tonique' },
];

const USAGES = [
  { value: '', label: 'Tous usages' },
  { value: 'infusion', label: 'Infusion' },
  { value: 'huile-essentielle', label: 'Huile essentielle' },
  { value: 'teinture', label: 'Teinture mère' },
  { value: 'poudre', label: 'Poudre' },
];

const SORT_OPTIONS = [
  { value: 'name-asc', label: 'Nom (A-Z)' },
  { value: 'name-desc', label: 'Nom (Z-A)' },
  { value: 'newest', label: 'Plus récent' },
  { value: 'popular', label: 'Plus populaire' },
];

const PlantFilters = ({ onFilterChange }: PlantFiltersProps) => {
  const [filters, setFilters] = useState<FilterState>({
    category: '',
    usage: '',
    sortBy: 'name-asc',
  });

  const handleChange = (key: keyof FilterState, value: string) => {
    const updated = { ...filters, [key]: value };
    setFilters(updated);
    onFilterChange?.(updated);
  };

  const handleReset = () => {
    const reset = { category: '', usage: '', sortBy: 'name-asc' };
    setFilters(reset);
    onFilterChange?.(reset);
  };

  return (
    <div className="bg-white rounded-xl border border-gray-200 p-4 shadow-sm">
      <div className="flex flex-wrap gap-3 items-center">
        <h3 className="text-sm font-semibold text-gray-700 mr-2">Filtres :</h3>

        {/* Category filter */}
        <select
          value={filters.category}
          onChange={(e) => handleChange('category', e.target.value)}
          className="text-sm border border-gray-300 rounded-lg px-3 py-1.5 focus:outline-none focus:ring-2 focus:ring-green-500"
        >
          {CATEGORIES.map((opt) => (
            <option key={opt.value} value={opt.value}>{opt.label}</option>
          ))}
        </select>

        {/* Usage filter */}
        <select
          value={filters.usage}
          onChange={(e) => handleChange('usage', e.target.value)}
          className="text-sm border border-gray-300 rounded-lg px-3 py-1.5 focus:outline-none focus:ring-2 focus:ring-green-500"
        >
          {USAGES.map((opt) => (
            <option key={opt.value} value={opt.value}>{opt.label}</option>
          ))}
        </select>

        {/* Sort */}
        <select
          value={filters.sortBy}
          onChange={(e) => handleChange('sortBy', e.target.value)}
          className="text-sm border border-gray-300 rounded-lg px-3 py-1.5 focus:outline-none focus:ring-2 focus:ring-green-500"
        >
          {SORT_OPTIONS.map((opt) => (
            <option key={opt.value} value={opt.value}>{opt.label}</option>
          ))}
        </select>

        {/* Reset */}
        <button
          onClick={handleReset}
          className="text-sm text-gray-500 hover:text-red-500 underline transition-colors ml-auto"
        >
          Réinitialiser
        </button>
      </div>
    </div>
  );
};

export default PlantFilters;
