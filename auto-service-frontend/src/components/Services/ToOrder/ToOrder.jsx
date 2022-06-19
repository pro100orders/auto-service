import React, {useEffect, useState} from 'react';
import {Button, FormControl, InputLabel, MenuItem, Select} from "@mui/material";
import {useNavigate} from "react-router-dom";
import $api from "../../../http";
import {toastr} from "react-redux-toastr";

const ToOrder = ({setOpen, service}) => {

    const [car, setCar] = useState();
    const [cars, setCars] = useState([]);

    const navigate = useNavigate();

    useEffect(() => {
        $api.get("/user/cars")
            .then((response) => {
                setCars(response.data);
            })
            .catch(reason => {
                toastr.error("Auto service", "Виникли технічні проблеми");
            });
    }, []);

    const order = () => {
        $api.post("/user/orders", {carId: car.id, serviceId: service.id})
            .then((response) => {
                setOpen(false);
                toastr.success("Auto service", "Замовлення успішно сформовано");
            })
            .catch(reason => {
                toastr.error("Auto service", "Виникли технічні проблеми");
            });
    }

    return (
        <div>
            <FormControl sx={{width: "300px", margin: "auto", display: "flex", justifyContent: "center"}}>
                <InputLabel>Статус замовлення</InputLabel>
                <Select
                    labelId="demo-simple-select-helper-label"
                    id="demo-simple-select-helper"
                    value={car}
                    label="Size"
                    fullWidth={true}
                    variant={"filled"}
                    onChange={e => {
                        setCar(e.target.value);
                    }}
                >
                    {
                        cars &&
                        cars.map(car => (
                            <MenuItem value={car}>{car.brand + " " + car.model}</MenuItem>
                        ))
                    }
                </Select>
            </FormControl>
            <Button
                onClick={() => order()}
                variant="contained"
                fullWidth={true}
                disableElevation={true}
                sx={{
                    marginTop: 2
                }}
            >
                Записатись
            </Button>
        </div>
    );
};

export default ToOrder;