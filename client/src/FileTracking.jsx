import 'bootstrap/dist/css/bootstrap.min.css';  
import React, { useState } from 'react';
import './table.css'
const FileTracking = ({ file, isComment }) => {

    const renderHeader = () => {
        let headerElement = ['user', 'type', 'change', 'date']
        if(isComment){
            headerElement = ['user', 'comment', 'date']
        }
        return headerElement.map((key, index) => {
            return <th key={index}>{key.toUpperCase()}</th>
        })
    }

    let comments = file.operations.comments;
    let others = file.operations.others;
    const renderBody = () => {
        return comments && comments.map((uid,idx) => {
            return (
                <tr key={idx}>
                    <td>{uid.user}</td>
                    <td>{uid.comment}</td>
                    <td>{uid.date}</td>
                </tr>
            )
        })
    }

    const renderOthersBody = () => {
        return others && others.map((uid,idx) => {
            return (
                <tr key={idx}>
                    <td>{uid.user}</td>
                    <td>{uid.type}</td>
                    <td>{uid.change}</td>
                    <td>{uid.date}</td>
                </tr>
            )
        })
    }

    return (
        <>
        <center>
            <table id='table-view'>
                <thead>
                    <tr>{renderHeader()}</tr>
                </thead>
                <tbody>
                    {isComment ? renderBody() :renderOthersBody()}
                </tbody>
            </table>
            </center>
        </>
    )

}


export default FileTracking;