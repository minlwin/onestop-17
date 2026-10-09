import { Button } from "@/components/ui/button";
import ManagementTemplate from "@/components/widgets/pages/management-template";

import * as client from "@/lib/service/client/management/subscription-plan.client"
import { Pencil } from "lucide-react";
import Link from "next/link";

export default async function SubscriptionPlanDetailsPage(props : PageProps<'/management/master/plans/[id]'>) {

    const { id } = await props.params
    const response = await client.findById(id)

    return (
        <ManagementTemplate page="Details" links={[
            {name : "Subscription Plans", route : "/management/master/plans"}
        ]}>
            <nav className="flex justify-end">
                <Button nativeButton={false} render={
                    <Link href={`/management/master/plans/${id}/edit`} />
                }>
                    <Pencil /> Edit Plan
                </Button>
            </nav>
            <pre>{JSON.stringify(response, null, 2)}</pre>
        </ManagementTemplate>
    )
}