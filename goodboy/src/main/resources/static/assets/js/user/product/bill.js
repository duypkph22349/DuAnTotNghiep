// LOAD BILL DETAIL

function findBillByCode(){
    const billCode = document.getElementById("bill_code").value;

    if(billCode !== "" && billCode !== null){
        axios.get("/client/bill/find-by-code/"+billCode).then((e)=>{
                window.location.href = "/client/bill/detail/"+e.data.id
        }).catch((e)=>{
            new Notify({
                status: "error",
                title: "Không tìm thấy hóa đơn",
                effect: "fade",
                speed: 300,
                customClass: "",
                customIcon: "",
                showIcon: true,
                showCloseButton: false,
                autoclose: true,
                autotimeout: 3000,
                gap: 20,
                distance: 20,
                type: 1,
                position: "right top",
                customWrapper: "",
            });
        })
    }else{
        new Notify({
            status: "error",
            title: "Bạn phải nhập mã hóa đơn ",
            effect: "fade",
            speed: 300,
            customClass: "",
            customIcon: "",
            showIcon: true,
            showCloseButton: false,
            autoclose: true,
            autotimeout: 3000,
            gap: 20,
            distance: 20,
            type: 1,
            position: "right top",
            customWrapper: "",
        });
    }
}