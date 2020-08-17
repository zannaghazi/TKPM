import React, {useState} from 'react';
import { Row, Container, Col, Form, FormControl, Button } from 'react-bootstrap';
import MySelectedBox from './MySelectedBox'
import styles from '../static/styles.module.css'
import { useHistory } from "react-router-dom";
import config from '../../../../../asset/config.json'
import { connect } from 'react-redux';
import * as actions from '../../../../../actions/index';

function Body(props) {
    const history = useHistory();
    const [type, setType] = useState("0");
    const [query, setQuery] = useState("");

    function search(event) {
        let typeSearch = "status";
        switch (type) {
            case '0': typeSearch = "status";
                break;
            case '1': typeSearch = "ID";
                break;
            case '2': typeSearch = "rentName";
                break;
            case '3': typeSearch = "rentID";
                break;
            default: typeSearch = "status";
                break;
        }
        console.log("type", typeSearch, type);

        let body = {
            type: typeSearch,
            key: query
        }
        console.log(body);
        let url = config.severAPi.hostUrl + ":8081/book/search_book";
        fetch(url, {
            method: "post",
            headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
            body: JSON.stringify(body)
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
                props.onSetListBook(data);
                props.bindEventShow(false);
            });

    }

    function NewBook(event) {
        history.push("/dashboard/new_book");
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
                    <MySelectedBox byValue={byValue}/>
                    <FormControl type="text" placeholder="Tìm kiếm" className={["mr-sm-2", styles.myQueryInput].join(" ")} onChange={handleChange}/>
                </Form>
            </Row>
            <Row className="mt-4 mb-4">
                <Col xs={5}>

                </Col>
                <Col xs={7} className="d-flex justify-content-end">
                    <Button variant="primary" className={styles.myButtonSearch} onClick={NewBook}><i className="fa fa-plus"></i>&nbsp;Thêm sách mới</Button>
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
        onSetListBook: (listBook) => {
            dispatch(actions.setListBook(listBook));
        }
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Body);