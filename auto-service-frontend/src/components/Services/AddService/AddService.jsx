import React, {useState} from 'react';
import {useNavigate} from "react-router-dom";
import {Button, Input, Typography} from "@mui/material";
import $api from "../../../http";

import './AddService.scss';
import {toastr} from "react-redux-toastr";

const AddService = ({setOpen}) => {

    const [service, setService] = useState({});
    const [file, setFile] = useState(null);

    const navigate = useNavigate();

    const fileSelectorHandler = (e) => {
        setFile(e.target.files[0]);
    }

    const handleClick = () => {
        $api.post("/services", service)
            .then((response) => {
                if (file != null) {
                    const image = new FormData();
                    image.append("image", file);
                    $api.post("/services/" + response.data.id, image);
                }
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
                    Додавання сервісу
                </Typography>
            </div>
            <form>
                <div className="formInput">
                    <label>Назва</label>
                    <Input
                        id={"name"}
                        type={"text"}
                        placeholder="Сервіс"
                        onChange={e => setService(prevState => ({...prevState, name: e.target.value}))}
                    />
                </div>
                <div className="formInput">
                    <label>Фото</label>
                    <input type={"file"} onChange={fileSelectorHandler}/>
                </div>
                <div className="formInput">
                    <label>Ціна</label>
                    <Input
                        id={"price"}
                        type={"number"}
                        placeholder="200"
                        onChange={e => setService(prevState => ({...prevState, price: e.target.value}))}
                    />
                </div>
                <div className="formInput">
                    <label>Час</label>
                    <Input
                        id={"time"}
                        type={"text"}
                        placeholder="2 дня"
                        onChange={e => setService(prevState => ({...prevState, time: e.target.value}))}
                    />
                </div>
                <div className="formInput">
                    <label>Опис</label>
                    <Input
                        id={"description"}
                        type={"text"}
                        multiline
                        placeholder="Опис"
                        onChange={e => setService(prevState => ({...prevState, description: e.target.value}))}
                    />
                </div>
                <button onClick={handleClick}>Добавити</button>
            </form>
        </div>
    );
};

export default AddService;