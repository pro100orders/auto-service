import React, {useState} from 'react';
import {useNavigate} from "react-router-dom";
import {Button, Input, Typography} from "@mui/material";
import $api from "../../../http";

import './AddUser.scss';
import {toastr} from "react-redux-toastr";

const AddUser = ({setOpen}) => {

    const [user, setUser] = useState({});

    const navigate = useNavigate();

    const handleClick = () => {
        $api.post("/registration", user)
            .then((response) => {
                navigate("/admin");
            })
            .catch(reason => {
                toastr.error("Auto service", "Виникли технічні проблеми");
            });
    };

    return (
        <div>
            <div style={{display: "flex", justifyContent: "space-between", padding: 10, width: "100%"}}>
                <Button variant={"outlined"} onClick={() => setOpen(false)}>
                    Назад
                </Button>
                <Typography variant={"h5"} style={{margin: "auto", width: 300}}>
                    Додавання користувача
                </Typography>
            </div>
            <form>
                <div className="formInput">
                    <label>Прізвище</label>
                    <Input
                        id={"surname"}
                        type={"text"}
                        placeholder="Васенков"
                        onChange={e => setUser(prevState => ({...prevState, surname: e.target.value}))}
                    />
                </div>
                <div className="formInput">
                    <label>Ім'я</label>
                    <Input
                        id={"name"}
                        type={"text"}
                        placeholder="Діма"
                        onChange={e => setUser(prevState => ({...prevState, name: e.target.value}))}
                    />
                </div>
                <div className="formInput">
                    <label>Email</label>
                    <Input
                        id={"email"}
                        type={"text"}
                        placeholder="bt769271@gmail.com"
                        onChange={e => setUser(prevState => ({...prevState, email: e.target.value}))}
                    />
                </div>
                <div className="formInput">
                    <label>Номер телефону</label>
                    <Input
                        id={"phone"}
                        type={"text"}
                        placeholder="380972553991"
                        onChange={e => setUser(prevState => ({...prevState, phone: e.target.value}))}
                    />
                </div>
                <div className="formInput">
                    <label>Пароль</label>
                    <Input
                        id={"password"}
                        type={"password"}
                        onChange={e => setUser(prevState => ({...prevState, password: e.target.value}))}
                    />
                </div>
                <button onClick={handleClick}>Добавити</button>
            </form>
        </div>
    );
};

export default AddUser;