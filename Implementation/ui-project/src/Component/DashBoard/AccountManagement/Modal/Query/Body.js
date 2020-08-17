import React, { useState } from 'react';
import { Row, Container, Col, Form, FormControl, Button } from 'react-bootstrap';
import { connect } from 'react-redux';
import * as actions from '../../../../../actions/index';
import MySelectedBox from './MySelectedBox'
import styles from '../static/styles.module.css'
import { useHistory } from "react-router-dom";
import config from '../../../../../asset/config.json'

function Body(props) {
    const history = useHistory();
    const [type, setType] = useState(0);
    const [query, setQuery] = useState("");
    function NewAccount(event) {
        history.push("/dashboard/new_account");
    }

    function search(event) {
        let typeSearch = "username";
        switch (type) {
            case '0': typeSearch = "username";
                break;
            case '1': typeSearch = "fullname"
                break;
            default: typeSearch = "username";
                break;
        }
        let body = {
            type: typeSearch,
            key: query
        }
        console.log(body);
        fetch(config.severAPi.hostUrl + ":8083/system/search_account", {
            method: "post",
            headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
            body: JSON.stringify(body)
        })
            .then(res => res.json())
            .then(data => {
                console.log("result", data);
                props.onSetListAccount(data);
                props.bindEventShow(false);
            });
    }

    function NewAccount(event) {
        history.push("/dashboard/new_account");
    }

    function byValue(data) {
        console.log(data);
        setType(data);
    }

    function handleChange(event) {
        setQuery(event.target.value);
    }

    return (
        <Container>
            <Row className="mt-5 ml-5">
                <Form inline className={styles.maxWidth}>
                    <MySelectedBox byValue={byValue} />
                    <FormControl type="text" placeholder="Tìm kiếm" className={["mr-sm-2", styles.myQueryInput].join(" ")} onChange={handleChange} />
                </Form>
            </Row>
            <Row className="mt-4 mb-4">
                <Col xs={5}>

                </Col>
                <Col xs={7} className="d-flex justify-content-end">
                    <Button variant="primary" className={styles.myButtonSearch} onClick={NewAccount}><i className="fa fa-plus"></i>&nbsp;Tạo tài khoản</Button>
                    <Button variant="success" className={styles.myButtonSearch} onClick={search}><i className="fa fa-search"></i>&nbsp;Tìm kiếm</Button>
                </Col>
            </Row>
        </Container>
    );
}

const mapStateToProps = state => {
    return {

    }
}

const mapDispatchToProps = (dispatch, props) => {
    return {
        onSetListAccount: (listAccount) => {
            dispatch(actions.setListAccount(listAccount));
        }
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Body);