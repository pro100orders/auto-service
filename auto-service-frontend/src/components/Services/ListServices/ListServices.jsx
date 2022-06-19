import React, {useState} from 'react';
import {Button, Typography} from "@mui/material";
import {
    DataGrid,
    GridToolbarColumnsButton,
    GridToolbarContainer,
    GridToolbarDensitySelector,
    GridToolbarExport,
    GridToolbarFilterButton
} from "@mui/x-data-grid";
import {servicesColumns} from "../../columns";
import $api from "../../../http";
import AddService from "../AddService/AddService";
import './ListServices.scss';
import EditService from "../EditService/EditService";
import {toastr} from "react-redux-toastr";

function CustomToolbar() {
    return (
        <GridToolbarContainer>
            <GridToolbarColumnsButton/>
            <GridToolbarFilterButton/>
            <GridToolbarDensitySelector/>
            <GridToolbarExport/>
        </GridToolbarContainer>
    );
}

const ListServices = ({services, setServices, isLoading}) => {

    const [openAdd, setOpenAdd] = useState(false);
    const [openEdit, setOpenEdit] = useState(false);
    const [edit, setEdit] = useState(0);

    const handleDelete = (id) => {
        console.log(id)
        $api.delete("/services/" + id)
            .then(response => {
                setServices(services.filter(service => service.id !== id));
            })
            .catch(reason => {
                toastr.error("Auto service", "Виникли технічні проблеми");
            });
    }

    const actionColumns = [
        {
            field: "action",
            headerName: "Дії",
            width: 200,
            renderCell: (params) => {
                return (
                    <div className="cellAction">
                        <div
                            className="updateButton"
                            onClick={() => {
                                setEdit(params.row.id);
                                setOpenEdit(true);
                            }}
                        >
                            Редагувати
                        </div>
                        <div
                            className="deleteButton"
                            onClick={() => handleDelete(params.row.id)}
                        >
                            Видалити
                        </div>
                    </div>
                );
            },
        },
    ];

    return (
        <>
            {
                (openAdd || openEdit) ?
                    (
                        <>
                            {
                                openAdd &&
                                <AddService setOpen={setOpenAdd}/>
                            }
                            {
                                openEdit &&
                                <EditService edit={edit} setOpen={setOpenEdit}/>
                            }
                        </>
                    )
                    :
                    (
                        isLoading ?
                            <div>
                                <Typography variant={"h5"} style={{margin: "auto", width: 300}}>
                                    Загрузка даних...
                                </Typography>
                            </div>
                            :
                            <div>
                                <div style={{display: "flex", justifyContent: "space-between", padding: 10}}>
                                    <Typography variant={"h4"}>
                                        Список сервісів
                                    </Typography>
                                    <Button variant={"outlined"} onClick={() => setOpenAdd(true)}>
                                        Додати сервіс
                                    </Button>
                                </div>
                                <div>
                                    {
                                        services && services.length === 0 ?
                                            <div>
                                                <Typography variant={"h5"} style={{margin: "auto", width: 300}}>
                                                    Список пустий
                                                </Typography>
                                            </div>
                                            :
                                            <div style={{height: 700, width: '100%'}}>
                                                <DataGrid
                                                    columns={servicesColumns.concat(actionColumns)}
                                                    rows={services}
                                                    components={{
                                                        Toolbar: CustomToolbar,
                                                    }}
                                                />
                                            </div>
                                    }
                                </div>
                            </div>
                    )
            }
        </>
    );
};

export default ListServices;