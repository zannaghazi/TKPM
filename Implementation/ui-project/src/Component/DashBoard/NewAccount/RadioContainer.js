import React from 'react';
import { Row, Col } from 'react-bootstrap';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import styles from '../static/styles.module.css';

function RadioContainer(props) {
    const [value, setValue] = React.useState("1");

    const handleChange = (event) => {
        console.log(event.target.value);
        setValue(event.target.value);
        props.byData(event.target.value);
    };

    return (
        <Row>
            <Col xs={2} className={["d-flex justify-content-end align-items-center", styles.myLabel].join(" ")}>
                {props.label}
            </Col>
            <Col xs={6}>
                <RadioGroup row  aria-label="gender" name="gender1" value={value} onChange={handleChange}>
                    <FormControlLabel value="1" control={<Radio />} label="Female" />
                    <FormControlLabel value="2" control={<Radio />} label="Male" />
                </RadioGroup>
            </Col>
        </Row>
    );
}

export default RadioContainer;