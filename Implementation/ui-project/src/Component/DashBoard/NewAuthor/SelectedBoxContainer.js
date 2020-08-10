import React from 'react';
import styles from '../static/styles.module.css';
import { Container, Row, Col } from 'react-bootstrap';
import { makeStyles } from '@material-ui/core/styles';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';
import Button from '@material-ui/core/Button';

function SeletedBoxContainer(props) {
    const [open, setOpen] = React.useState(false);
    const [role, setRole] = React.useState(1);
    const handleOpen = () => {
        setOpen(true);
    };
    const handleClose = () => {
        setOpen(false);
    };

    const handleChange = (event) => {
        setRole(event.target.value);
    };

    return (
        <Row>
            <Col xs={2} className={["d-flex justify-content-end align-items-center", styles.myLabel].join(" ")}>
                {props.label}
            </Col>
            <Col xs={6}>
                <FormControl>
                    <Select
                        labelId="demo-controlled-open-select-label"
                        id="demo-controlled-open-select"
                        open={open}
                        onClose={handleClose}
                        onOpen={handleOpen}
                        value={role}
                        onChange={handleChange}
                    >
                        <MenuItem value={1}>Thủ thư</MenuItem>
                        <MenuItem value={2}>Độc giả</MenuItem>
                        <MenuItem value={3}>Admin</MenuItem>
                    </Select>
                </FormControl>
            </Col>
        </Row>
    );
}

export default SeletedBoxContainer;