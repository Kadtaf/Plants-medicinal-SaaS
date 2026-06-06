import { Route } from "react-router-dom";
import HomePage from "@/pages/public/HomePage.tsx";

export const publicRoutes = (
    <>
        <Route path="/" element={<HomePage />} />
    </>
);
