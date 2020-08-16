import React from 'react';
import {Row, Col} from 'react-bootstrap';
import styles from '../static/styles.module.css';
import { useState, useEffect } from 'react';

function InputContainer(props) {
    const [type, setType] = useState("text");
    const [value, setValue]= useState("");

    useEffect(() => {
        if(props.value){
            setValue(props.value);
        }
        
      }, [props.value]);
    
    function handleChange(event){
        setValue(event.target.value);
        props.byData(event.target.value);
    } 

    return (
        <Row>
            <Col xs={2} className={["d-flex justify-content-end align-items-center", styles.myLabel].join(" ")}>
                {props.label}
                </Col>
            <Col xs={6}>
                <input type={type} className={[styles.myInput, props.is_disabled ? styles.myDisabled : ""].join(" ")} onChange={handleChange} value={value || ""}/>
            </Col>
        </Row>
    );
}

export default InputContainer;