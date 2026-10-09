import ManagementTemplate from "@/components/widgets/pages/management-template";
import * as client from "@/lib/service/client/management/subscription-plan.client"
import PlanEditComponent from "../../_client/plan-edit-component";

export default async function SubscriptionPlanEditPage(props : PageProps<'/management/master/plans/[id]/edit'>) {

    const { id } = await props.params
    const plan = await client.findById(id)

    return (
        <ManagementTemplate page="Edit" links={[
            {name : "Subscription Plans", route : "/management/master/plans"}
        ]}>
            <PlanEditComponent plan={plan} />
        </ManagementTemplate>
    )
}