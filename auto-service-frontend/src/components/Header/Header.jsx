import React from 'react';
import {
    AppBar,
    Avatar,
    Box,
    Button,
    Container,
    IconButton,
    Menu,
    MenuItem,
    Toolbar,
    Tooltip,
    Typography
} from "@mui/material";
import MenuIcon from '@mui/icons-material/Menu';
import CarRepairIcon from '@mui/icons-material/CarRepair';
import {useDispatch, useSelector} from "react-redux";
import {useNavigate} from "react-router-dom";

const Header = () => {

    const roles = useSelector(state => state.auth.user.roles);

    const [navbarMenu, setNavbarMenu] = React.useState(null);
    const [userMenu, setUserMenu] = React.useState(null);

    const dispatch = useDispatch();

    const navigate = useNavigate();

    const logout = () => {
        if (localStorage.getItem("token"))
            localStorage.removeItem("token");
        dispatch({type: "SET_AUTH", payload: {token: '', user: {roles: ['ROLE_GUEST']}}});
    }

    return (
        <AppBar position="static">
            <Container maxWidth="xl">
                <Toolbar disableGutters>
                    <CarRepairIcon sx={{display: {xs: 'none', md: 'flex'}, mr: 1}}/>
                    <Typography
                        variant="h6"
                        noWrap
                        component="a"
                        href="/"
                        sx={{
                            mr: 2,
                            display: {xs: 'none', md: 'flex'},
                            fontFamily: 'monospace',
                            fontWeight: 700,
                            letterSpacing: '.3rem',
                            color: 'inherit',
                            textDecoration: 'none',
                        }}
                    >
                        AUTO SERVICE
                    </Typography>

                    <Box sx={{flexGrow: 1, display: {xs: 'flex', md: 'none'}}}>
                        <IconButton
                            size="large"
                            aria-label="account of current user"
                            aria-controls="menu-appbar"
                            aria-haspopup="true"
                            onClick={e => setNavbarMenu(e.currentTarget)}
                            color="inherit"
                        >
                            <MenuIcon/>
                        </IconButton>
                        <Menu
                            id="menu-appbar"
                            anchorEl={navbarMenu}
                            anchorOrigin={{
                                vertical: 'bottom',
                                horizontal: 'left',
                            }}
                            keepMounted
                            transformOrigin={{
                                vertical: 'top',
                                horizontal: 'left',
                            }}
                            open={Boolean(navbarMenu)}
                            onClose={e => setNavbarMenu(null)}
                            sx={{
                                display: {xs: 'block', md: 'none'},
                            }}
                        >
                            <MenuItem onClick={e => setNavbarMenu(null)}>
                                <Typography textAlign="center">Services</Typography>
                            </MenuItem>
                            <MenuItem onClick={e => setNavbarMenu(null)}>
                                <Typography textAlign="center">Cars</Typography>
                            </MenuItem>
                        </Menu>
                    </Box>
                    <CarRepairIcon sx={{display: {xs: 'flex', md: 'none'}, mr: 1}}/>
                    <Typography
                        variant="h5"
                        noWrap
                        component="a"
                        href=""
                        sx={{
                            mr: 2,
                            display: {xs: 'flex', md: 'none'},
                            flexGrow: 1,
                            fontFamily: 'monospace',
                            fontWeight: 700,
                            letterSpacing: '.3rem',
                            color: 'inherit',
                            textDecoration: 'none',
                        }}
                    >
                        AUTO SERVICE
                    </Typography>
                    <Box sx={{flexGrow: 1, display: {xs: 'none', md: 'flex'}}}>
                        <Button
                            onClick={e => navigate("/services")}
                            sx={{my: 2, color: 'white', display: 'block'}}
                        >
                            ??????????????
                        </Button>
                    </Box>
                    {
                        roles && roles.includes("ROLE_ADMIN") &&
                        <Box sx={{flexGrow: 1, display: {xs: 'none', md: 'flex'}}}>
                            <Button
                                onClick={e => navigate("/admin")}
                                sx={{my: 2, color: 'white', display: 'block'}}
                            >
                                ??????????
                            </Button>
                        </Box>
                    }
                    {
                        roles && roles.includes("ROLE_GUEST") ?
                            <Button
                                onClick={e => navigate("/login")}
                                sx={{
                                    my: 2,
                                    color: 'white',
                                    display: 'block',
                                    border: "1px solid white",
                                    borderRadius: "5px"
                                }}
                            >
                                ??????????????????????
                            </Button>
                            :
                            <Box sx={{flexGrow: 0}}>
                                <Tooltip title="Open settings">
                                    <IconButton onClick={e => setUserMenu(e.currentTarget)} sx={{p: 0}}>
                                        <Avatar alt="Remy Sharp" src="/static/images/avatar/2.jpg"/>
                                    </IconButton>
                                </Tooltip>
                                <Menu
                                    sx={{mt: '45px'}}
                                    id="menu-appbar"
                                    anchorEl={userMenu}
                                    anchorOrigin={{
                                        vertical: 'top',
                                        horizontal: 'right',
                                    }}
                                    keepMounted
                                    transformOrigin={{
                                        vertical: 'top',
                                        horizontal: 'right',
                                    }}
                                    open={Boolean(userMenu)}
                                    onClose={e => setUserMenu(null)}
                                >
                                    <MenuItem onClick={e => {setUserMenu(null);navigate("/profile");}}>
                                        <Typography textAlign="center">
                                            ??????????????
                                        </Typography>
                                    </MenuItem>
                                    <MenuItem onClick={e => {setUserMenu(null);navigate("/cars");}}>
                                        <Typography textAlign="center">
                                            ?????? ????????????
                                        </Typography>
                                    </MenuItem>
                                    <MenuItem onClick={e => {setUserMenu(null);navigate("/orders");}}>
                                        <Typography textAlign="center">
                                            ?????? ????????????????????
                                        </Typography>
                                    </MenuItem>
                                    <MenuItem onClick={e => {setUserMenu(null);logout();}}>
                                        <Typography textAlign="center" style={{color: "red"}}>
                                            ??????????
                                        </Typography>
                                    </MenuItem>
                                </Menu>
                            </Box>
                    }
                </Toolbar>
            </Container>
        </AppBar>
    );
};

export default Header;