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
import {carsColumns} from "../../columns";
import $api from "../../../http";
import AddCar from "../AddCar/AddCar";
import './ListCar.scss';
import EditCar from "../EditCar/EditCar";
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

const ListCar = ({cars, setCars, isLoading}) => {

    const [openAdd, setOpenAdd] = useState(false);
    const [openEdit, setOpenEdit] = useState(false);
    const [edit, setEdit] = useState(0);

    const handleDelete = (id) => {
        $api.delete("/cars/" + id)
            .then(response => {
                setCars(cars.filter(car => car.id !== id));
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

    console.log(cars);
    return (
        <>
            {
                (openAdd || openEdit) ?
                    (
                        <>
                            {
                                openAdd &&
                                <AddCar setOpen={setOpenAdd}/>
                            }
                            {
                                openEdit &&
                                <EditCar edit={edit} setOpen={setOpenEdit}/>
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
                                        Список машин
                                    </Typography>
                                    <Button variant={"outlined"} onClick={() => setOpenAdd(true)}>
                                        Додати машину
                                    </Button>
                                </div>
                                <div>
                                    {
                                        cars && cars.length === 0 ?
                                            <div>
                                                <Typography variant={"h5"} style={{margin: "auto", width: 300}}>
                                                    Список пустий
                                                </Typography>
                                            </div>
                                            :
                                            <div style={{height: 700, width: '100%'}}>
                                                <DataGrid
                                                    columns={carsColumns.concat(actionColumns)}
                                                    rows={cars}
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

export default ListCar;