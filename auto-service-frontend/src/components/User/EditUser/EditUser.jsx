import React, {useEffect, useState} from 'react';
import {useNavigate} from "react-router-dom";
import {Button, Input, Typography} from "@mui/material";
import $api from "../../../http";

import '../AddUser/AddUser.scss';
import {toastr} from "react-redux-toastr";

const EditUser = ({edit, setOpen}) => {

    const [user, setUser] = useState({});

    const navigate = useNavigate();

    const handleClick = () => {
        console.log(user);
        $api.put("/admin/users", user)
            .then((response) => {
                navigate("/admin");
            })
            .catch(reason => {
                toastr.error("Auto service", "Виникли технічні проблеми");
            });
    };

    useEffect(() => {
        $api.get("/admin/users/" + edit)
            .then((response) => {
                console.log(response.data);
                setUser(response.data);
            })
            .catch(reason => {
                toastr.error("Auto service", "Виникли технічні проблеми");
            });
    }, []);

    return (
        <div>
            <div style={{display: "flex", justifyContent: "space-between", padding: 10, width: "100%"}}>
                <Button variant={"outlined"} onClick={() => setOpen(false)}>
                    Назад
                </Button>
                <Typography variant={"h5"} style={{margin: "auto", width: 300}}>
                    Редагування користувача
                </Typography>
            </div>
            <form>
                <div className="formInput">
                    <label>Прізвище</label>
                    <Input
                        id={"surname"}
                        type={"text"}
                        value={user.surname}
                        placeholder="Васенков"
                        onChange={e => setUser(prevState => ({...prevState, surname: e.target.value}))}
                    />
                </div>
                <div className="formInput">
                    <label>Ім'я</label>
                    <Input
                        id={"name"}
                        type={"text"}
                        value={user.name}
                        placeholder="Діма"
                        onChange={e => setUser(prevState => ({...prevState, name: e.target.value}))}
                    />
                </div>
                <div className="formInput">
                    <label>Email</label>
                    <Input
                        id={"email"}
                        type={"text"}
                        value={user.email}
                        placeholder="bt769271@gmail.com"
                        onChange={e => setUser(prevState => ({...prevState, email: e.target.value}))}
                    />
                </div>
                <div className="formInput">
                    <label>Номер телефону</label>
                    <Input
                        id={"phone"}
                        type={"text"}
                        value={user.phone}
                        placeholder="380972553991"
                        onChange={e => setUser(prevState => ({...prevState, phone: e.target.value}))}
                    />
                </div>
                <button onClick={handleClick}>Відредагувати</button>
            </form>
        </div>
    );
};

export default EditUser;