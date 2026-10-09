import ManagementTemplate from "@/components/widgets/pages/management-template";
import PaymentInfoEditComponent from "../_client/payment-info-edit-component";

export default function CreatePaymentInfoPage() {
    return (
        <ManagementTemplate page="Create" links={[{
            name : "Payment Information", route : "/management/master/payments"
        }]}>
            <PaymentInfoEditComponent />
        </ManagementTemplate>
    )
}