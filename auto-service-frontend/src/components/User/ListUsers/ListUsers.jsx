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
import {usersColumns} from "../../columns";
import $api from "../../../http";
import AddUser from "../AddUser/AddUser";
import './ListUsers.scss';
import EditUser from "../EditUser/EditUser";
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

const ListUsers = ({users, setUsers, isLoading}) => {

    const [openAdd, setOpenAdd] = useState(false);
    const [openEdit, setOpenEdit] = useState(false);
    const [edit, setEdit] = useState(0);

    const handleDelete = (id) => {
        console.log(id)
        $api.delete("/admin/users/" + id)
            .then(response => {
                setUsers(users.filter(user => user.id !== id));
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
                                <AddUser setOpen={setOpenAdd}/>
                            }
                            {
                                openEdit &&
                                <EditUser edit={edit} setOpen={setOpenEdit}/>
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
                                        Список користувачів
                                    </Typography>
                                    <Button variant={"outlined"} onClick={() => setOpenAdd(true)}>
                                        Додати користувача
                                    </Button>
                                </div>
                                <div>
                                    {
                                        users && users.length === 0 ?
                                            <div>
                                                <Typography variant={"h5"} style={{margin: "auto", width: 300}}>
                                                    Список пустий
                                                </Typography>
                                            </div>
                                            :
                                            <div style={{height: 700, width: '100%'}}>
                                                <DataGrid
                                                    columns={usersColumns.concat(actionColumns)}
                                                    rows={users}
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

export default ListUsers;