import { Outlet } from "react-router-dom";
import Navbar from "@/widgets/navbar/Navbar";
import Footer from "@/widgets/footer/Footer";

export default function MainLayout() {
  return (
    <div className="min-h-screen bg-[var(--color-bg)] text-[var(--color-text)]">
      <Navbar />
      <main className="mx-auto max-w-6xl px-4 py-6 md:py-8">
        <Outlet />
      </main>
      <Footer />
    </div>
  );
}
