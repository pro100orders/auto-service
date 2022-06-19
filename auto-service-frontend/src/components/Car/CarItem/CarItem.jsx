import React from 'react';
import {Button, Card, CardActionArea, CardActions, CardContent, CardMedia, Typography} from "@mui/material";

const CarItem = ({service}) => {
    return (
        <Card sx={{width: 345, height: 450,  margin: 1}}>
            <CardActionArea>
                <CardMedia
                    component="img"
                    height="200"
                    image={"http://localhost:8080/files/" + service.image}
                    alt={service.name}
                />
                <CardContent sx={{maxHeight: 150}}>
                    <Typography gutterBottom variant="h5" component="div">
                        {service.name}
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                        Ціна: {service.price}
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                        Час: {service.time}
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                        {service.description}
                    </Typography>
                </CardContent>
            </CardActionArea>
            <CardActions>
                <Button size="small" color="primary">
                    Записатись
                </Button>
            </CardActions>
        </Card>
    );
};

export default CarItem;