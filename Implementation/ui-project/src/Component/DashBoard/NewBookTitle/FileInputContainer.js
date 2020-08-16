import React from 'react';
import styles from '../static/styles.module.css';
import { Row, Col } from 'react-bootstrap';

function FileInputContainer(props) {

    function onChangeHandler(event){
        console.log(event.target.files[0]);
        props.byData(event.target.files[0]);
    }
    
    return (
        <Row>
            <Col xs={2} className={["d-flex justify-content-end align-items-center", styles.myLabel].join(" ")}>
                {props.label}
            </Col>
            <Col xs={6}>
            <div><input type="file" name="file" onChange={onChangeHandler} accept="image/*"/></div>
            </Col>
        </Row>
    );
}

export default FileInputContainer;