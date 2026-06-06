import { Route } from "react-router-dom";
import Dashboard from "@/pages/admin/dashboard/Dashboard.tsx";

export const adminRoutes = (
    <>
        <Route path="/admin" element={<Dashboard />} />
    </>
);
