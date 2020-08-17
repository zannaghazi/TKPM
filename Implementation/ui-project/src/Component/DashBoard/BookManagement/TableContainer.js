import React from 'react';
import {Table, Button} from 'react-bootstrap';
import styles from '../static/styles.module.css'
import { useHistory } from "react-router-dom";
import { connect } from 'react-redux';
import * as actions from '../../../actions/index';

function TableContainer(props) {
    const history = useHistory();
    
    function editItem(event, data){
        console.log("mydata", data);
        if(!data){
            return;
        }
        props.onSetBookTitleEdit(data);
        history.push("/dashboard/edit_book_title/"+ data.ISBN);
    }

    const listItems = props.listBook.map((element, index) =>
        <tr key={index}>
            <td>{element.ID}</td>
            <td>{element.ISBN}</td>
            <td>{element.statusName}</td>
            <td>
                <Button variant="primary" className={styles.myButtonDetail} onClick={(event) => editItem(event, element)}><i className="fa fa-edit"></i></Button>
                <Button variant="danger" className={[styles.myButtonDetail, "ml-2"].join(" ")}><i className="fa fa-trash"></i></Button>
            </td>
        </tr>
    );

    return (
        <Table striped bordered hover>
            <thead>
                <tr>
                    <th width={'5%'}>ID</th>
                    <th width={'45%'}>ISBN</th>
                    <th width={'30%'}>Trạng thái</th>
                    <th width={'20%'}>
                    </th>
                </tr>
            </thead>
            <tbody>
            {listItems}
            </tbody>
        </Table>
    );
}

const mapStateToProps = state => {
	return {
		
	}
}

const mapDispatchToProps = (dispatch, props) => {
	return {
		onSetBookTitleEdit: (item) => {
            dispatch(actions.setBookTitleEdit(item));
        }
	}
}

export default connect(mapStateToProps, mapDispatchToProps)(TableContainer);