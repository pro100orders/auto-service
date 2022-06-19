import React, {useEffect, useState} from 'react';
import {useNavigate} from "react-router-dom";
import {Button, Input, Typography} from "@mui/material";
import $api from "../../../http";

import './../AddService/AddService.scss';
import {toastr} from "react-redux-toastr";

const EditService = ({edit, setOpen}) => {

    const [service, setService] = useState({});
    const [file, setFile] = useState(null);

    const navigate = useNavigate();

    const fileSelectorHandler = (e) => {
        setFile(e.target.files[0]);
    }

    const handleClick = () => {
        $api.put("/services", service)
            .then((response) => {
                if (file != null) {
                    const image = new FormData();
                    image.append("image", file);
                    $api.put("/services/" + response.data.id, image);
                }
                navigate("/admin");
            })
            .catch(reason => {
                toastr.error("Auto service", "Виникли технічні проблеми");
            });
    };

    useEffect(() => {
        console.log(edit);
        $api.get("/services/" + edit)
            .then((response) => {
                console.log(response.data);
                setService(response.data);
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
                    Редагування сервісу
                </Typography>
            </div>
            <form>
                <div className="formInput">
                    <label>Назва</label>
                    <Input
                        id={"name"}
                        type={"text"}
                        value={service.name}
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
                        value={service.price}
                        placeholder="200"
                        onChange={e => setService(prevState => ({...prevState, price: e.target.value}))}
                    />
                </div>
                <div className="formInput">
                    <label>Час</label>
                    <Input
                        id={"time"}
                        type={"text"}
                        value={service.time}
                        placeholder="2 дня"
                        onChange={e => setService(prevState => ({...prevState, time: e.target.value}))}
                    />
                </div>
                <div className="formInput">
                    <label>Опис</label>
                    <Input
                        id={"description"}
                        type={"text"}
                        value={service.description}
                        multiline
                        placeholder="Опис"
                        onChange={e => setService(prevState => ({...prevState, description: e.target.value}))}
                    />
                </div>
                <button onClick={handleClick}>Відредагувати</button>
            </form>
        </div>
    );
};

export default EditService;