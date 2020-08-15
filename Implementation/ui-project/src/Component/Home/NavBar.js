import React from 'react';
import { Navbar, Nav, NavDropdown } from 'react-bootstrap';
import styles from './static/styles.module.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import config from '../../asset/config.json';
import * as actions from '../../actions/index';
import { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import {NavLink} from 'react-router-dom'

function NavBar(props) {
    const [listTypeBook, setListTypeBook] = useState([]);
    useEffect(() => {
        let url = config.severAPi.hostUrl + ":8081/book/get_all_type";
        fetch(url, {
            method: "get",
            headers: { "Content-Type": "application/json" },
        })
            .then(res => res.json())
            .then(data => {
                console.log("type", data);
                let listItems = [];
                for(let i =0; i<data.length; i++){
                    listItems.push(<NavLink to={"/list_request?type=type&key="+data[i]} key={i} id="basic-nav-dropdown" className={[styles.myDropdown, i <(data.length - 1) ? styles.myBorderRight : ""].join(" ")}>
                    {data[i]}
                    </NavLink>)
                }
                setListTypeBook(listItems);
            });
    }, []);

    return (
        <Navbar bg="dark" expand="lg" variant="dark" className={styles.myFixed}>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
                <Nav className={[styles.myNav, "d-flex align-items-center"].join(" ")}>
                    <NavLink to="/" className={[ styles.myBorderRight, styles.mySpacing, styles.myHome].join(" ")}><b>Trang chủ</b></NavLink>
                    {listTypeBook}
                </Nav>
            </Navbar.Collapse>
        </Navbar>
    );
}

const mapStateToProps = state => {
    return {
        list: state.list_new_book_titles
    }
}

const mapDispatchToProps = (dispatch, props) => {
    return {
        onSetListTypeBook: (listTypeBook) => {
            dispatch(actions.setListTypeBook(listTypeBook));
        }
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(NavBar);