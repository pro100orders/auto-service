import React, {useEffect, useState} from 'react';
import $api from "../../http";
import {Typography} from "@mui/material";
import ServiceItem from "../../components/Services/ServiceItem/ServiceItem";
import {toastr} from "react-redux-toastr";

const Services = () => {

    const [services, setServices] = useState([]);
    const [isLoading, setLoading] = useState(true);

    useEffect(() => {
        setLoading(true);
        $api.get("/services")
            .then(response => {
                setServices(response.data);
                setLoading(false);
            })
            .catch(reason => {
                toastr.error("Auto service", "Виникли технічні проблеми");
            })
    }, []);

    return (
        <>
            {
                isLoading ?
                    <div>
                        <Typography variant={"h5"} style={{margin: "auto", width: 300}}>
                            Загрузка даних...
                        </Typography>
                    </div>
                    :
                    <div style={{marginTop: 10}}>
                        {
                            services && services.length === 0 ?
                                <div>
                                    <Typography variant={"h5"} style={{margin: "auto", width: 300}}>
                                        Сервісів немає
                                    </Typography>
                                </div>
                                :
                                <div style={{margin: "auto", width: 1200, display: "flex", flexWrap: "wrap"}}>
                                    {
                                        services.map(service => (
                                            <ServiceItem service={service}/>
                                        ))
                                    }
                                </div>
                        }
                    </div>
            }
        </>
    );
};

export default Services;