import React, {useEffect, useState} from 'react';
import $api from "../../http";
import {Button, Typography} from "@mui/material";
import {
    DataGrid,
    GridToolbarColumnsButton,
    GridToolbarContainer,
    GridToolbarDensitySelector, GridToolbarExport,
    GridToolbarFilterButton
} from "@mui/x-data-grid";
import {servicesColumns} from "../columns";
import ListUsers from "./ListUsers/ListUsers";
import {toastr} from "react-redux-toastr";

const Users = () => {

    const [users, setUsers] = useState([]);
    const [isLoading, setLoading] = useState(true);

    useEffect(() => {
        setLoading(true);
        $api.get("/admin/users")
            .then(response => {
                setUsers(response.data);
                setLoading(false);
            })
            .catch(reason => {
                toastr.error("Auto service", "Виникли технічні проблеми");
            })
    }, []);

    return (
        <>
            <ListUsers users={users} setUsers={setUsers} isLoading={isLoading}/>
        </>
    );
};

export default Users;