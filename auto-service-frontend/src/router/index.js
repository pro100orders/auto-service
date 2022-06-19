import React, {useEffect, useState} from 'react';
import Home from "../pages/Home/Home";
import {useSelector} from "react-redux";
import Admin from "../pages/Admin/Admin";
import Services from "../pages/Services/Services";
import Profile from "../pages/Profile/Profile";
import LoginForm from "../components/LoginForm/LoginForm";
import RegistrationForm from "../components/RegistrationForm/RegistrationForm";
import Orders from "../pages/Orders/Orders";

const AppRoutes = () => {

    const roles = useSelector(state => state.auth.user.roles);

    const [routes, setRoutes] = useState([]);

    useEffect(() => {
        setRoutes([
            {path: "/", component: Home},
            {path: "/services", component: Services},
            {path: "/services/:id", component: Services},
        ]);

        if (roles && roles.includes("ROLE_GUEST")) {
            const guestRoutes = [
                {path: '/login', component: LoginForm},
                {path: '/registration', component: RegistrationForm},
            ];

            setRoutes(routes => routes = routes.concat(guestRoutes));
        }

        if (roles && roles.includes("ROLE_USER")) {
            const userRoutes = [
                {path: "/profile", component: Profile},
                {path: "/orders", component: Orders},
            ];

            setRoutes(routes => routes = routes.concat(userRoutes));
        }

        if (roles && roles.includes("ROLE_ADMIN")) {
            const adminRoutes = [
                {path: "/admin", component: Admin},
            ];

            setRoutes(routes => routes = routes.concat(adminRoutes));
        }
    }, [roles]);


    console.log(routes);
    return routes;
}

export default AppRoutes;