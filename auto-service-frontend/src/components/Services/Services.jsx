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
import ListServices from "./ListServices/ListServices";

const Services = () => {

    const [services, setServices] = useState([]);
    const [isLoading, setLoading] = useState(true);

    useEffect(() => {
        setLoading(true);
        $api.get("/services")
            .then(response => {
                setServices(response.data);
                setLoading(false);
            })
            .catch(reason => {
                toastr.error("Auto service", "Виникли технічні проблеми");
            })
    }, []);

    return (
        <>
            <ListServices services={services} setServices={setServices} isLoading={isLoading}/>
        </>
    );
};

export default Services;