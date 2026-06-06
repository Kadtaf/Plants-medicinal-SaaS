import { Route } from "react-router-dom";
import Login from "@/pages/auth/login/Login.tsx";
import Register from "@/pages/auth/register/Register.tsx";

export const authRoutes = (
    <>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
    </>
);
