import React, {useState} from 'react';
import { Row, Container, Col, Form, FormControl, Button } from 'react-bootstrap';
import MySelectedBox from './MySelectedBox'
import styles from '../static/styles.module.css'
import config from '../../../../../asset/config.json'
import { connect } from 'react-redux';
import * as actions from '../../../../../actions/index';

function Body(props) {
    const [type, setType] = useState(0);
    const [query, setQuery] = useState("");

    function search(event) {
        let typeSearch = "name";
        switch (type) {
            case '0': typeSearch = "name";
                break;
            case '1': typeSearch = "ISBN"
                break;
            case '2': typeSearch = "ID"
                break;
            default: typeSearch = "name";
                break;
        }
        let body = {
            type: typeSearch,
            key: query,
            currentUserID: props.userLogin.id
        }
        console.log(body);
        fetch(config.severAPi.hostUrl + ":8082/reader/search", {
            method: "post",
            headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
            body: JSON.stringify(body)
        })
            .then(res => res.json())
            .then(data => {
                if(data.result){
                    props.onSetListReader(data.data);
                    props.bindEventShow(false);
                }
            });
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
                    <MySelectedBox  byValue={byValue}/>
                    <FormControl type="text" placeholder="Tìm kiếm" className={["mr-sm-2", styles.myQueryInput].join(" ")} onChange={handleChange}/>
                </Form>
            </Row>
            <Row className="mt-4 mb-4">
                <Col xs={5}>

                </Col>
                <Col xs={7} className="d-flex justify-content-end">
                    <Button variant="success" className={styles.myButtonSearch} onClick={search}><i className="fa fa-search"></i>&nbsp;Tìm kiếm</Button>
                </Col>
            </Row>
        </Container>
    );
}

const mapStateToProps = state => {
    return {
        userLogin: state.user_login
    }
}

const mapDispatchToProps = (dispatch, props) => {
    return {
        onSetListReader: (listReader) => {
            dispatch(actions.setListReader(listReader));
        }
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Body);