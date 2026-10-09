import ManagementTemplate from "@/components/widgets/pages/management-template"
import * as client from "@/lib/service/client/management/payment-info.client"
import PaymentInfoEditComponent from "../../_client/payment-info-edit-component"

export default async function EditPaymentInfoPage(props : PageProps<'/management/master/payments/[id]/edit'>) {
    const { id } = await props.params
    const info = await client.findById(id)

    return (
        <ManagementTemplate page="Edit" links={[{
            name : "Payment Information", route : "/management/master/payments"
        }]}>
            <PaymentInfoEditComponent info={info} />
        </ManagementTemplate>
    )
}