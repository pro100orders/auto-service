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
import ListCar from "./ListCars/ListCar";

const Cars = () => {

    const [cars, setCars] = useState([]);
    const [isLoading, setLoading] = useState(true);

    useEffect(() => {
        setLoading(true);
        $api.get("/admin/cars")
            .then(response => {
                setCars(response.data);
                setLoading(false);
            })
            .catch(reason => {
                toastr.error("Auto service", "Виникли технічні проблеми");
            })
    }, []);

    return (
        <>
            <ListCar cars={cars} setCars={setCars} isLoading={isLoading}/>
        </>
    );
};

export default Cars;