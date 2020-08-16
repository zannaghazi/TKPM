import React, {useState, useEffect} from 'react';
import styles from '../static/styles.module.css';
import Select from 'react-select'
import {Row, Col} from 'react-bootstrap'

function SeletedBoxContainer(props) {
    const [options, setOptions]= useState([]);

    useEffect(() => {
        console.log("My List", props.data)
        if(props.data){
            let temp = props.data.map(obj => {
                let rObj = {};
                rObj["value"]=obj.id;
                rObj["label"]=obj.name;
                return rObj;
            })

            setOptions(temp);
        }
    }, [props.data]);

    function handleChange(event){
        props.byData(event.value);
    }

    return (
        <Row className = "mt-1">
            <Col xs={2} className={["d-flex justify-content-end align-items-center", styles.myLabel].join(" ")}>
                {props.label}
            </Col>
            <Col xs={6} className = "pl-3">
                <Select className={styles.myAuthorSelect} options={options} onChange={handleChange}/>
            </Col>
        </Row>
    );
}

export default SeletedBoxContainer;