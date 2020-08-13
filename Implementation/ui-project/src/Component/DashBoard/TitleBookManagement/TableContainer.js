import React from 'react';
import { Table, Button } from 'react-bootstrap';
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
        history.push("/dashboard/edit_book_title");
    }

    const listItems = props.listBookTitle.map((element, index) =>
        <tr key={index}>
            <td>{element.ISBN}</td>
            <td>{element.name}</td>
            <td>{element.author}</td>
            <td>{element.location}</td>
            <td>{element.publisher}</td>
            <td>{element.releaseDate}</td>
            <td>{element.type}</td>
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
                    <th width={'2%'}>ISBN</th>
                    <th width={'15%'}>Tên</th>
                    <th width={'15%'}>Tác giả</th>
                    <th >Vị trí</th>
                    <th width={'15%'}>Nhà phát hành</th>
                    <th width={'10%'}>Ngày phát hành</th>
                    <th width={'15%'}>Thể loại</th>
                    <th width={'8%'}>
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