import ManagementTemplate from "@/components/widgets/pages/management-template";
import PlanEditComponent from "../_client/plan-edit-component";

export default function CreateSubscrptionPlanPage() {
    return (
        <ManagementTemplate page="Create" links={[
            {name : "Subscription Plans", route : "/management/master/plans"}
        ]}>
            <PlanEditComponent />
        </ManagementTemplate>
    )
}