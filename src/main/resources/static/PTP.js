$(document).ready(function () {

    $("#apportDiv").hide(); // Hide Apportionment Table on Lod.

    var currPTPId;

    var ptpJsonObject = [];

    /** Function for PTP Table cell on click
     Overview: Show Apportionment Table Data for the selected Row.
     Each PTP table row identifid by PTP ID.
     PTP ID is auto generted.
     **/
    $(document).on("click", "#ptpTable td", function (e) {

        //Getting PTP ID of the selected row.
        var ptpid = $('#ptpTable tbody tr').eq($(this).parent().index()).find('td input').eq(3).val();
        currPTPId = ptpid;

        //Show Apport Table if PTP Id is not null
        if (ptpid) {
            $("#apportDiv").show();
        }
        else {
            $("#apportDiv").hide();
        }

        //Creating REST URL to invoke to fetch Apportionment Details for selected PTPId

        var url = "http://localhost:8080/ptp/" + ptpid + "/apportionment";
        console.log(url);

        //AJAX Call invoked only when PTPId is not null
        if (ptpid) {
            $.ajax({
                type: "GET",
                url: url,
                dataType: 'json',
                async: false,
                success: function (data) {
                    console.log("My Response " + JSON.stringify(data));
                    console.log("Aportionment Data Length=" + data.length);

                    //If No Apportionments exist for PTP Id, then clean up the Apport Table in UI
                    if (data.length == 0) {

                        if ($("#apportTable tbody tr").length >= 1) {
                            //Remove rows > 0. Cannot delete first row is template for new row creations.
                            $("#apportTable tr:gt(1)").remove();

                            //Reset first row
                            $('#apportTable tbody tr').eq(0).find('td').eq(1).find('input').val("");
                            $('#apportTable tbody tr').eq(0).find('td').eq(2).find('input').val("");
                            $('#apportTable tbody tr').eq(0).find('td').eq(3).find('input').val("");
                            $('#apportTable tbody tr').eq(0).find('td').eq(4).find('input').val("");
                        }

                    }
                    else {// If data exists, the repaint the table with the fetched data
                        //Removing extra rows >  data length
                        $("#apportTable tr").slice(data.length + 1).remove();
                        for (i in data) {

                            //Adding rows for each data set
                            if ($("#apportTable tbody tr").length < data.length) {
                                var table = document.getElementById("apportTable");
                                var numRows = table.rows.length;
                                var cells = table.getElementsByTagName("td");
                                var row = table.insertRow(numRows);
                                var cell1 = row.insertCell(0);
                                var cell2 = row.insertCell(1);
                                var cell3 = row.insertCell(2);
                                var cell4 = row.insertCell(3);
                                var cell5 = row.insertCell(4);


                                cell1.innerHTML = cells[0].innerHTML;
                                cell2.innerHTML = cells[1].innerHTML;
                                cell3.innerHTML = cells[2].innerHTML;
                                cell4.innerHTML = cells[3].innerHTML;
                                cell5.innerHTML = cells[4].innerHTML;
                            }
                            $('#apportTable tbody tr').eq(parseInt(i)).find('td').eq(1).find('input').val(data[i].loanType);
                            $('#apportTable tbody tr').eq(parseInt(i)).find('td').eq(2).find('input').val(data[i].loanAmount);
                            $('#apportTable tbody tr').eq(parseInt(i)).find('td').eq(3).find('input').val(data[i].loanApportionment);
                            $('#apportTable tbody tr').eq(parseInt(i)).find('td').eq(4).find('input').val(data[i].apportionmentId);
                        }

                    }
                }
                ,
                error: function () {
                    console.log("Error");
                }

            });
        }

    });

// To be written. Delete PTP rows.
    $("#deletePTP").click(function () {
        alert("Delete");
        $("#apportDiv").hide();
    });

//Adding  PTPs
    $("#addPTP").click(function () {
        $("#apportDiv").hide();
        var table = document.getElementById("ptpTable");
        var numRows = table.rows.length;
        var cells = table.getElementsByTagName("td");
        var row = table.insertRow(numRows);
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);

        cell1.innerHTML = cells[0].innerHTML;
        cell2.innerHTML = cells[1].innerHTML;
        cell3.innerHTML = cells[2].innerHTML;
        cell4.innerHTML = cells[3].innerHTML;

    });

//Adding Apportionemnts
    $("#addApport").click(function () {
        $("#apportDiv").show();
        var table = document.getElementById("apportTable");
        var numRows = table.rows.length;
        var cells = table.getElementsByTagName("td");
        var row = table.insertRow(numRows);
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);
        var cell5 = row.insertCell(4);


        cell1.innerHTML = cells[0].innerHTML;
        cell2.innerHTML = cells[1].innerHTML;
        cell3.innerHTML = cells[2].innerHTML;
        cell4.innerHTML = cells[3].innerHTML;
        cell5.innerHTML = cells[4].innerHTML;

    });

    $("#savePTP").click(function () {
        var json = [];
        $('#ptpTable').find('tbody tr').each(function () {
            var obj = {};
            var ptpId = $('.txtPTPId', this).val();
            // alert("ptpId="+ptpId);
            var ptpType = $('.txtPtpType', this).val();
            //alert(ptpType);
            var borrName = $('.txtBorrName', this).val();
            //alert(borrName);
            obj['ptpType'] = ptpType;
            obj['borowerName'] = borrName;


            if (ptpId) {
                //alert("Not Nill");
            }
            else {
                json.push(obj);
            }
        });


        //alert("Lenght ="+json.length);
        //alert(JSON.stringify(json));
//        $.post("http://localhost:8080/ptp/",
//        JSON.stringify(json),
//        function(data,status){
//            alert("Data: " + data + "\nStatus: " + status);
//        });

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/ptp/",
            data: JSON.stringify(json),
            contentType: "application/json",
            dataType: 'json',
            async: false,
            success: function (data) {
                // alert("Hello");
                // alert("Response "+JSON.stringify(data));
                var count = 0;
                $('#ptpTable').find('tbody tr').each(function () {
                    var ptpId = $('.txtPTPId', this).val();
                    if (ptpId) {
                        count = parseInt(count) + parseInt(1);
                    }
                });
                // alert("count=="+count);
                for (i in data) {
                    //count = count+1;
                    // alert(JSON.stringify(data[i].pid));
                    //alert( 'INsidee' + $('#ptpTable tbody tr').eq(0).find('td input').eq(2).val() );
                    //alert($('#ptpTable tbody tr').eq(i).find('td').eq(2).val());
                    //alert('i='+i);
                    // alert('count+i='+parseInt(count)+parseInt(1));
                    $('#ptpTable tbody tr').eq(parseInt(count) + parseInt(i)).find('td').eq(3).find('input').val(data[i].pid);
                    //$('.txtPTPId').val('200');
                }
                //alert (count);
            },
            error: function () {
                alert("Error");
            }

        });


    });


/// Saving Apportionments
    $("#saveApport").click(function () {

        var json = [];//JSON array to hold Apportionment values
        $('#apportTable').find('tbody tr').each(function () {
            var obj = {};
            var appId = $('.txtApportId', this).val();
            var loanType = $('.txtLoanType', this).val();
            var apportionment = $('.txtApportionment', this).val();
            var amount = $('.txtAmount', this).val();
            //Creating JSON elements
            obj['loanType'] = loanType;
            obj['loanAmount'] = apportionment;
            obj['loanApportionment'] = amount;

            if (appId) {
                //If Apportionment ID exists, then dont add to JSON object.
            }
            else {
                //Adding to JSON array
                json.push(obj);
            }
        });
        var url = "http://localhost:8080/ptp/" + currPTPId + "/apportionment/"
        console.log("Apportionment Save URL= " + url);

        $.ajax({
            type: "POST",
            url: url,
            data: JSON.stringify(json),
            contentType: "application/json",
            dataType: 'json',
            async: false,
            success: function (data) {

                var count = 0;
                $('#apportTable').find('tbody tr').each(function () {
                    var apportId = $('.txtApportId', this).val();
                    if (apportId) {
                        count = parseInt(count) + parseInt(1);
                    }
                });

                for (i in data) {
                    //Set Apportionment ID for each apportionment created.
                    $('#apportTable tbody tr').eq(parseInt(count) + parseInt(i)).find('td').eq(4).find('input').val(data[i].apportionmentId);

                }

            },
            error: function (xhr, status, error) {
                console.log("AJAX error" + xhr.responseText);
            }

        });


    });
}); // Document Ready End
