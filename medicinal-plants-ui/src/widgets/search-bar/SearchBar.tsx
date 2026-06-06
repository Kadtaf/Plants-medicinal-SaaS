import { useState } from 'react';

interface SearchBarProps {
  onSearch: (query: string) => void;
  placeholder?: string;
  initialValue?: string;
}

const SearchBar = ({
  onSearch,
  placeholder = 'Rechercher une plante...',
  initialValue = '',
}: SearchBarProps) => {
  const [query, setQuery] = useState(initialValue);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSearch(query.trim());
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setQuery(e.target.value);
    if (e.target.value === '') {
      onSearch('');
    }
  };

  return (
    <form onSubmit={handleSubmit} className="flex w-full max-w-xl">
      <div className="relative flex-1">
        <span className="absolute inset-y-0 left-3 flex items-center text-gray-400">
          &#128269;
        </span>
        <input
          type="text"
          value={query}
          onChange={handleChange}
          placeholder={placeholder}
          className="w-full pl-10 pr-4 py-2 rounded-l-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent text-sm"
          aria-label="Rechercher"
        />
      </div>
      <button
        type="submit"
        className="px-4 py-2 bg-green-600 text-white rounded-r-lg hover:bg-green-700 transition-colors text-sm font-medium"
      >
        Rechercher
      </button>
    </form>
  );
};

export default SearchBar;
