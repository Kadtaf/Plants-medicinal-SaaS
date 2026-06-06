import { BrowserRouter, Route, Routes } from "react-router-dom";
import { publicRoutes } from "./public.routes";
import { authRoutes } from "./auth.routes";
import { adminRoutes } from "./admin.routes";
import MainLayout from "@/widgets/main-layout/MainLayout";

export function AppRouter() {
    return (
        <BrowserRouter>
            <Routes>
                <Route element={<MainLayout />}>
                    {publicRoutes}
                    {authRoutes}
                    {adminRoutes}
                </Route>
            </Routes>
        </BrowserRouter>
    );
}