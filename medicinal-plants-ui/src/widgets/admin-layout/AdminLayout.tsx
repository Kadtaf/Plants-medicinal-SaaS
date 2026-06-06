import { Outlet } from 'react-router-dom';

const AdminLayout = () => {
  return (
    <div className="flex min-h-screen bg-gray-50">
      {/* Sidebar */}
      <aside className="w-64 bg-white border-r border-gray-200 shadow-sm flex flex-col">
        <div className="p-6 border-b border-gray-200">
          <h2 className="text-lg font-bold text-green-700">Admin Panel</h2>
          <p className="text-xs text-gray-500 mt-1">Medicinal Plants SaaS</p>
        </div>
        <nav className="flex-1 p-4 space-y-1">
          <a
            href="/admin"
            className="flex items-center gap-3 px-3 py-2 rounded-lg text-sm font-medium text-gray-700 hover:bg-green-50 hover:text-green-700 transition-colors"
          >
            Dashboard
          </a>
          <a
            href="/admin/plants"
            className="flex items-center gap-3 px-3 py-2 rounded-lg text-sm font-medium text-gray-700 hover:bg-green-50 hover:text-green-700 transition-colors"
          >
            Plants
          </a>
          <a
            href="/admin/users"
            className="flex items-center gap-3 px-3 py-2 rounded-lg text-sm font-medium text-gray-700 hover:bg-green-50 hover:text-green-700 transition-colors"
          >
            Users
          </a>
          <a
            href="/admin/orders"
            className="flex items-center gap-3 px-3 py-2 rounded-lg text-sm font-medium text-gray-700 hover:bg-green-50 hover:text-green-700 transition-colors"
          >
            Orders
          </a>
        </nav>
        <div className="p-4 border-t border-gray-200">
          <p className="text-xs text-gray-400">&copy; {new Date().getFullYear()} Medicinal Plants</p>
        </div>
      </aside>

      {/* Main content */}
      <main className="flex-1 flex flex-col overflow-auto">
        <header className="bg-white border-b border-gray-200 px-6 py-4 shadow-sm">
          <h1 className="text-xl font-semibold text-gray-800">Administration</h1>
        </header>
        <div className="flex-1 p-6">
          <Outlet />
        </div>
      </main>
    </div>
  );
};

export default AdminLayout;
