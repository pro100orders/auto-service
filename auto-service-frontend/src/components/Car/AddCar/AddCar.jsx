import React, {useEffect, useState} from 'react';
import {useNavigate} from "react-router-dom";
import {Button, FormControl, Input, MenuItem, Select, Typography} from "@mui/material";
import $api from "../../../http";

import './AddCar.scss';

const AddCar = ({setOpen}) => {

    const [car, setCar] = useState({});
    const [user, setUser] = useState({});

    const [users, setUsers] = useState([]);
    const [fuels, setFuels] = useState([]);
    const [transmissions, setTransmissions] = useState([]);
    const [driveTypes, setDriveTypes] = useState([]);

    const navigate = useNavigate();

    useEffect(() => {
        $api.get("/admin/users")
            .then(response => {
                setUsers(response.data);
            })
            .catch(reason => {
                toastr.error("Auto service", "Виникли технічні проблеми");
            });
    }, []);

    useEffect(() => {
        $api.get("/cars/fuels")
            .then(response => {
                setFuels(response.data);
            })
            .catch(reason => {
                //toastr.error("Bookstore", "Виникли технічні проблеми");
            });
    }, []);

    useEffect(() => {
        $api.get("/cars/transmissions")
            .then(response => {
                setTransmissions(response.data);
            })
            .catch(reason => {
                toastr.error("Auto service", "Виникли технічні проблеми");
            });
    }, []);

    useEffect(() => {
        $api.get("/cars/drive-types")
            .then(response => {
                setDriveTypes(response.data);
            })
            .catch(reason => {
                toastr.error("Auto service", "Виникли технічні проблеми");
            });
    }, []);

    const handleClick = () => {
        const newcar = {
            ...car,
            userId: user
        };
        $api.post("/admin/cars", newcar)
            .then((response) => {
                navigate("/admin");
            })
            .catch(reason => {
                console.log(reason);
            });
    }

    return (
        <div>
            <div style={{display: "flex", justifyContent: "space-between", padding: 10, width: "100%"}}>
                <Button variant={"outlined"} onClick={() => setOpen(false)}>
                    Назад
                </Button>
                <Typography variant={"h5"} style={{margin: "auto", width: 300}}>
                    Додавання машини
                </Typography>
            </div>
            <form>
                <div className="formInput">
                    <label>Власник</label>
                    <FormControl sx={{m: 1, minWidth: 120}} size="small">
                        <Select
                            id="userId"
                            value={user}
                            onChange={e => setUser(e.target.value)}
                            style={{width: 200}}
                        >
                            {
                                users &&
                                users.map((user) => (
                                    <MenuItem key={user.id} value={user.id}>
                                        {user.surname + " " + user.name}
                                    </MenuItem>
                                ))
                            }
                        </Select>
                    </FormControl>
                </div>
                <div className="formInput">
                    <label>Бренд</label>
                    <Input
                        id={"brand"}
                        type={"text"}
                        placeholder="Бренд"
                        onChange={e => setCar(prevState => ({...prevState, brand: e.target.value}))}
                    />
                </div>
                <div className="formInput">
                    <label>Модель</label>
                    <Input
                        id={"model"}
                        type={"text"}
                        placeholder="Модель"
                        onChange={e => setCar(prevState => ({...prevState, model: e.target.value}))}
                    />
                </div>
                <div className="formInput">
                    <label>Рік випуску</label>
                    <Input
                        id={"year"}
                        type={"number"}
                        placeholder="200"
                        onChange={e => setCar(prevState => ({...prevState, year: e.target.value}))}
                    />
                </div>
                <div className="formInput">
                    <label>Номер</label>
                    <Input
                        id={"number"}
                        type={"text"}
                        placeholder="CE6752HB"
                        onChange={e => setCar(prevState => ({...prevState, number: e.target.value}))}
                    />
                </div>
                <div className="formInput">
                    <label>VIN Code</label>
                    <Input
                        id={"vinCode"}
                        type={"text"}
                        placeholder="VIN Code"
                        onChange={e => setCar(prevState => ({...prevState, vinCode: e.target.value}))}
                    />
                </div>
                <div className="formInput">
                    <label>Паливо</label>
                    <FormControl sx={{m: 1, minWidth: 120}} size="small">
                        <Select
                            id="fuel"
                            value={car.fuel}
                            onChange={e => setCar(prevState => ({...prevState, fuel: e.target.value}))}
                            style={{width: 200}}
                        >
                            {
                                fuels &&
                                fuels.map((fuel) => (
                                    <MenuItem key={fuel} value={fuel}>
                                        {fuel}
                                    </MenuItem>
                                ))
                            }
                        </Select>
                    </FormControl>
                </div>
                <div className="formInput">
                    <label>Коробка</label>
                    <FormControl sx={{m: 1, minWidth: 120}} size="small">
                        <Select
                            id="transmission"
                            value={car.transmission}
                            onChange={e => setCar(prevState => ({...prevState, transmission: e.target.value}))}
                            style={{width: 200}}
                        >
                            {
                                transmissions &&
                                transmissions.map((transmission) => (
                                    <MenuItem key={transmission} value={transmission}>
                                        {transmission}
                                    </MenuItem>
                                ))
                            }
                        </Select>
                    </FormControl>
                </div>
                <div className="formInput">
                    <label>Тип приводу</label>
                    <FormControl sx={{m: 1, minWidth: 120}} size="small">
                        <Select
                            id="driveType"
                            value={car.driveType}
                            onChange={e => setCar(prevState => ({...prevState, driveType: e.target.value}))}
                            style={{width: 200}}
                        >
                            {
                                driveTypes &&
                                driveTypes.map((driveType) => (
                                    <MenuItem key={driveType} value={driveType}>
                                        {driveType}
                                    </MenuItem>
                                ))
                            }
                        </Select>
                    </FormControl>
                </div>
                <button onClick={handleClick}>Добавити</button>
            </form>
        </div>
    );
};

export default AddCar;